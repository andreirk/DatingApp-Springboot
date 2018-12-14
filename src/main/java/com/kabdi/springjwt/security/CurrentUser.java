package com.kabdi.springjwt.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;
/**
 * Created by Khalid Abdi
 */
@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal
public @interface CurrentUser {

}
