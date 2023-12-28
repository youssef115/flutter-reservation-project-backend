package com.youssef.flutterProjectBackend.service;

import com.youssef.flutterProjectBackend.enties.User;
import com.youssef.flutterProjectBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{
        @Autowired
        private UserRepository userRepository;
    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }
}
