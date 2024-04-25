package com.authentication.authentication.with.roles.controllers;

import com.authentication.authentication.with.roles.dto.UserListDTO;
import com.authentication.authentication.with.roles.models.ApplicationUser;
import com.authentication.authentication.with.roles.repository.RoleRepository;
import com.authentication.authentication.with.roles.repository.UserRepository;
import com.authentication.authentication.with.roles.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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
    public String sampleUserController(){
        return "User access level";
    }

//    @GetMapping("/testing")
//    public List<User> getAllUsers() {
//        return userService.getAllUsers();
//    }

}
