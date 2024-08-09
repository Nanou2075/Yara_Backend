package com.nanou.yaraBank.security.authentication;

import com.nanou.yaraBank.exception.Response.Response;
import com.nanou.yaraBank.security.password.ChangePasswordRequestDTO;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;
@CrossOrigin(origins = "localhost:4200")
public interface AuthenticationResource {

    @GetMapping("signOut")
    Response signOut();



    @PostMapping("signIn")
    Response authentication(@RequestBody AuthenticationRequest authenticationRequest);

    @PostMapping("refreshToken")
    @ResponseBody
    Response refreshToken(@RequestBody Map<String, String> refreshTokenRequest);

    @GetMapping(value = "profile")
    UserDetails profile(Principal principal);


    @PostMapping("changePassword")
    Response changePassword(@RequestBody ChangePasswordRequestDTO request, Principal principal);




}
