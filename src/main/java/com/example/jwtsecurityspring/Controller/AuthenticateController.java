package com.example.jwtsecurityspring.Controller;

import com.example.jwtsecurityspring.Model.JwtRequest;
import com.example.jwtsecurityspring.Model.JwtResponse;
import com.example.jwtsecurityspring.utility.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticateController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    @Qualifier("customUserService")
    UserDetailsService userDetailsService;

    @Autowired
    JwtUtility jwtUtility;

    @PostMapping("/authenticate")
    public JwtResponse generateToken(@RequestBody JwtRequest request) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (BadCredentialsException exception) {
            throw new Exception("Invalid username/Password");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtUtility.generateJwtToken(userDetails);
        return new JwtResponse(token);
    }
}
