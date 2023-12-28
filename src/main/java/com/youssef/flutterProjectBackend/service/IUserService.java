package com.youssef.flutterProjectBackend.service;

import com.youssef.flutterProjectBackend.enties.User;

public interface IUserService {
    public User getUserByEmail(String email);
}
