package com.kabdi.springjwt.controller;

import com.kabdi.springjwt.dtos.UserForDetailedDto;
import com.kabdi.springjwt.dtos.UserForListDto;
import com.kabdi.springjwt.dtos.UserForListDtoWrapper;
import com.kabdi.springjwt.dtos.UserForLoginDto;
import com.kabdi.springjwt.dtos.UserForRegisterDto;
import com.kabdi.springjwt.model.Role;
import com.kabdi.springjwt.model.RoleName;
import com.kabdi.springjwt.model.User;
import com.kabdi.springjwt.repository.RoleRepository;
import com.kabdi.springjwt.repository.UserRepository;
import com.kabdi.springjwt.security.JwtTokenProvider;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.Optional;

/**
 * Created by Khalid Abdi
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;
    
    @Autowired
    DozerBeanMapper dozerBeanMapper;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody UserForLoginDto userForLoginDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                		userForLoginDto.getUsername(),
                		userForLoginDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        Optional<User> userFromRepo = userRepository.findOneWithPhotos(userForLoginDto.getUsername());
        UserForListDto user = dozerBeanMapper.map(userFromRepo.get(), UserForListDto.class);
        String token = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new UserForListDtoWrapper(token, user));
        //return ResponseEntity.ok(userForListDto);
        //return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(UriComponentsBuilder b, @RequestBody UserForRegisterDto userForRegisterDto) {
    	
        if(userRepository.existsByUsername(userForRegisterDto.getUsername())) {
            return new ResponseEntity("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = dozerBeanMapper.map(userForRegisterDto, User.class);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));
       
        userRepository.save(user);
        
        UserForDetailedDto userForDetailedDto = dozerBeanMapper.map(user, UserForDetailedDto.class);
        //URI uri = ServletUriComponentsBuilder.fromPath("/Users/{idOfNewResource}").buildAndExpand(user.getId()).toUri();
        /*URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().
        		path("/{idOfNewResource}").buildAndExpand(user.getId()).toUri();*/
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().replacePath("/Users/{idOfNewResource}").buildAndExpand(user.getId()).toUri();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(uri);
       // return ResponseEntity.status(HttpStatus.CREATED).headers(responseHeaders).body(userForDetailedDto);
        return  new ResponseEntity(HttpStatus.CREATED);
    }
}
