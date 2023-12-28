package com.youssef.flutterProjectBackend.service;

import com.youssef.flutterProjectBackend.enties.User;
import com.youssef.flutterProjectBackend.models.AuthRequestModel;
import com.youssef.flutterProjectBackend.models.AuthResponseModel;
import com.youssef.flutterProjectBackend.models.RegisterRequestModel;
import com.youssef.flutterProjectBackend.models.Role;
import com.youssef.flutterProjectBackend.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService{
    @Value("${application.security.jwt.expiration}")
    private Long expiration;

    @Autowired
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Override
    public AuthResponseModel register(RegisterRequestModel registerRequestModel) {
        var user = User.builder()
                .firstname(registerRequestModel.getFirstname())
                .lastname(registerRequestModel.getLastname())
                .email(registerRequestModel.getEmail())
                .password(passwordEncoder.encode(registerRequestModel.getPassword()))
                .role(registerRequestModel.getRole())
                .CIN(registerRequestModel.getCin())
                .address(registerRequestModel.getAddress())
                .phone(registerRequestModel.getPhone())
                .build();
        userRepository.save(user);
        var jwtToken=jwtService.generateToken(user);
        return AuthResponseModel.builder()
                .accessToken(jwtToken)
                .statusCode(HttpStatus.OK.value())
                .message("the account is created successfully")
                .build();
    }

    @Override
    public AuthResponseModel login(AuthRequestModel authRequestModel) {
       authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(
                       authRequestModel.getEmail(),
                       authRequestModel.getPassword()
               )
       );
       var user=userRepository.findByEmail(authRequestModel.getEmail()).orElseThrow(()->new UsernameNotFoundException("user doesn't exist"));
       var jwtToken=jwtService.generateToken(user);
       return AuthResponseModel.builder()
               .accessToken(jwtToken)
               .message("you just login now successfully")
               .statusCode(HttpStatus.OK.value())
               .loginTime(System.currentTimeMillis())
               .expirationDuration(expiration)
               .build();
    }
}
