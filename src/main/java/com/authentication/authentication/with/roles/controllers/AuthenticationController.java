package com.authentication.authentication.with.roles.controllers;

import com.authentication.authentication.with.roles.dto.LoginResponseDTO;
import com.authentication.authentication.with.roles.dto.RegistrationDTO;
import com.authentication.authentication.with.roles.models.ApplicationUser;
import com.authentication.authentication.with.roles.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {


    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping("/register")
    public ApplicationUser registerUser(@RequestBody RegistrationDTO body){
        return authenticationService.registerUser(body.getUsername(), body.getPassword());
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody RegistrationDTO body){
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }

    @PostMapping("/init-admin")
    public String initAdmin(){
        return authenticationService.initAdmin();
    }
}