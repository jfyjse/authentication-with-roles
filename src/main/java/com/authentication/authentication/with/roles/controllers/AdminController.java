package com.authentication.authentication.with.roles.controllers;

import com.authentication.authentication.with.roles.dto.RegistrationDTO;
import com.authentication.authentication.with.roles.dto.UserListDTO;
import com.authentication.authentication.with.roles.models.ApplicationUser;
import com.authentication.authentication.with.roles.services.AuthenticationService;
import com.authentication.authentication.with.roles.services.UserService;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")

public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping("/register-user")
    public ResponseEntity<ApplicationUser> registerUser(@RequestBody RegistrationDTO body){
        return authenticationService.registerUser(body.getUsername(), body.getPassword());
    }


    @PostMapping("/register-admin")
    public ResponseEntity<ApplicationUser> registerAdmin(@RequestBody RegistrationDTO body){
        return authenticationService.registerAdmin(body.getUsername(), body.getPassword());
    }


    @GetMapping("/list-all-users")
    public List<UserListDTO> getAllUsers() {
        return userService.getAllUsers();
    }

}