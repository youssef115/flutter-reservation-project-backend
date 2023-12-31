package com.youssef.flutterProjectBackend.controller;

import com.youssef.flutterProjectBackend.enties.User;
import com.youssef.flutterProjectBackend.models.ResponseModel;
import com.youssef.flutterProjectBackend.models.UserResponse;
import com.youssef.flutterProjectBackend.service.IUserService;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/userinfo")
public class UserController {

    @Autowired
    private IUserService iUserService;
    @GetMapping("{email}")
    public ResponseModel<UserResponse> getUserByEmail(@PathVariable String email){
        User user=iUserService.getUserByEmail(email);
        var responseUser= UserResponse.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .cin(user.getCIN())
                .role(user.getRole())
                .build();
        return new ResponseModel(HttpStatus.OK.value(),"here the user details",responseUser);
    }
}
