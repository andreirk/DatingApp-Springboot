package com.kabdi.springjwt.security;

import com.kabdi.springjwt.model.User;
import com.kabdi.springjwt.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Khalid Abdi
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        // Let people login with username
        User user = userRepository.findByUsername(username)
        		.orElseThrow(() ->
                new UsernameNotFoundException("User not found with username : " + username)
);

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(int id) {
        User user = userRepository.findById(id).orElseThrow(
            () -> new RuntimeException(String.format("User not found with id : '%s'", id))
        );

        return UserPrincipal.create(user);
    }
}