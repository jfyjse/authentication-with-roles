package com.authentication.authentication.with.roles.services;

import com.authentication.authentication.with.roles.dto.LoginResponseDTO;
import com.authentication.authentication.with.roles.models.ApplicationUser;
import com.authentication.authentication.with.roles.models.Role;
import com.authentication.authentication.with.roles.repository.RoleRepository;
import com.authentication.authentication.with.roles.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public ApplicationUser registerUser(String username, String password){

        String encodedPassword = passwordEncoder.encode(password);
        if(roleRepository.findByAuthority("USER").isPresent()) {
            Role userRole = roleRepository.findByAuthority("USER").get();

            Set<Role> authorities = new HashSet<>();

            authorities.add(userRole);

            return userRepository.save(new ApplicationUser(0, username, encodedPassword, authorities));
        }
        else {
            String s = "";

            return registerUser(s,s);

        }
    }

    public LoginResponseDTO loginUser(String username, String password){

        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = tokenService.generateJwt(auth);

            return new LoginResponseDTO(userRepository.findByUsername(username).get(), token);

        } catch(AuthenticationException e){
            return new LoginResponseDTO(null, "");
        }
    }

    public String initAdmin(){
        Boolean apiValidator;
        String initalRun = "init done first time !!";
        String skipRun = "init already done skipping....... !!";

        if(roleRepository.findByAuthority("ADMIN").isPresent()) {
            apiValidator = Boolean.TRUE;
        }

        else {

            Role adminRole = roleRepository.save(new Role("ADMIN"));
            Role userRole = roleRepository.save(new Role("USER"));

            Set<Role> adminRoleSet = new HashSet<>();
            adminRoleSet.add(adminRole);

            Set<Role> userRoleSet = new HashSet<>();
            userRoleSet.add(userRole);

            ApplicationUser admin = new ApplicationUser(0, "admin", passwordEncoder.encode("password"), adminRoleSet);
            ApplicationUser user = new ApplicationUser(0, "user", passwordEncoder.encode("password"), userRoleSet);

            userRepository.save(admin);
            userRepository.save(user);

            apiValidator=Boolean.FALSE;
        }
        if (apiValidator){
            return skipRun;
        }
        else {
            return initalRun;
        }

    }

}
