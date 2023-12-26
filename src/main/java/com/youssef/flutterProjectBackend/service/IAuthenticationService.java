package com.youssef.flutterProjectBackend.service;

import com.youssef.flutterProjectBackend.models.AuthRequestModel;
import com.youssef.flutterProjectBackend.models.AuthResponseModel;
import com.youssef.flutterProjectBackend.models.RegisterRequestModel;

public interface IAuthenticationService {

    public AuthResponseModel register(RegisterRequestModel registerRequestModel);

    public AuthResponseModel login(AuthRequestModel authRequestModel);
}
