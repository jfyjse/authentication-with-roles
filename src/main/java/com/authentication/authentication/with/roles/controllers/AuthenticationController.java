package com.authentication.authentication.with.roles.controllers;

import com.authentication.authentication.with.roles.dto.LoginResponseDTO;
import com.authentication.authentication.with.roles.dto.RegistrationDTO;
import com.authentication.authentication.with.roles.models.ApplicationUser;
import com.authentication.authentication.with.roles.models.Role;
import com.authentication.authentication.with.roles.repository.RoleRepository;
import com.authentication.authentication.with.roles.repository.UserRepository;
import com.authentication.authentication.with.roles.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {


    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    public RoleRepository roleRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public PasswordEncoder passwordEncode;

    @PostMapping("/register")
    public ApplicationUser registerUser(@RequestBody RegistrationDTO body){
        return authenticationService.registerUser(body.getUsername(), body.getPassword());
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body){
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }

    @PostMapping("/init-admin")
    public String initAdmin(){
        return authenticationService.initAdmin();
    }
}