package com.authentication.authentication.with.roles.controllers;

import com.authentication.authentication.with.roles.repository.RoleRepository;
import com.authentication.authentication.with.roles.repository.UserRepository;
import com.authentication.authentication.with.roles.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    public UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Autowired
    public RoleRepository roleRepository;

    @GetMapping("/")
    public String sampleUserController() {
        return "User access level";
    }


}
