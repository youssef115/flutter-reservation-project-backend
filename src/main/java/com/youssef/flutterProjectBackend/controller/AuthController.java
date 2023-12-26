package com.youssef.flutterProjectBackend.controller;


import com.youssef.flutterProjectBackend.models.AuthRequestModel;
import com.youssef.flutterProjectBackend.models.AuthResponseModel;
import com.youssef.flutterProjectBackend.models.RegisterRequestModel;
import com.youssef.flutterProjectBackend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseModel> register(
            @RequestBody RegisterRequestModel request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseModel> authenticate(
            @RequestBody AuthRequestModel request
    ) {
        return ResponseEntity.ok(authenticationService.login(request));
    }
}
