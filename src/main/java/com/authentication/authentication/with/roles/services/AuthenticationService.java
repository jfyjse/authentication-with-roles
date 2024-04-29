package com.authentication.authentication.with.roles.services;

import com.authentication.authentication.with.roles.dto.LoginResponseDTO;
import com.authentication.authentication.with.roles.models.ApplicationUser;
import com.authentication.authentication.with.roles.models.Role;
import com.authentication.authentication.with.roles.repository.RoleRepository;
import com.authentication.authentication.with.roles.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<LoginResponseDTO> loginUser(String username, String password){


            if (userRepository.findByUsername(username).isPresent()){
                Authentication auth = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(username, password)
                );

                String token = tokenService.generateJwt(auth);

                return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDTO(userRepository.findByUsername(username).get(), token));

            }
            else {
                System.out.println("1222");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new LoginResponseDTO(null, ""));

            }
    }

    public String initAdmin(){
        Boolean apiValidator;
        String initialRun = "init done first time !!";
        String skipRun = "init already done skipping....... !!";

        if(roleRepository.findByAuthority("ADMIN").isPresent()) {
            apiValidator = Boolean.TRUE;
        }

        else {

            Role adminRole = roleRepository.save(new Role("ADMIN"));
            Role userRole = roleRepository.save(new Role("USER"));

            Set<Role> adminRoleAuthority = new HashSet<>();
            adminRoleAuthority.add(adminRole);

            Set<Role> userRoleAuthority = new HashSet<>();
            userRoleAuthority.add(userRole);

            ApplicationUser admin = new ApplicationUser(0, "admin", passwordEncoder.encode("password"), adminRoleAuthority);
            ApplicationUser user = new ApplicationUser(0, "user", passwordEncoder.encode("password"), userRoleAuthority);

            userRepository.save(admin);
            userRepository.save(user);

            apiValidator=Boolean.FALSE;
        }
        if (apiValidator){
            return skipRun;
        }
        else {
            return initialRun;
        }

    }

}
