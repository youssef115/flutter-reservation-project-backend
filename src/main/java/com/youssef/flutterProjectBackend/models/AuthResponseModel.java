package com.youssef.flutterProjectBackend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseModel{
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("status-code")
    private int statusCode;
    private String message;
    @JsonProperty("login-time")
    private Long loginTime;
    @JsonProperty("expiration-duration")
    private Long expirationDuration;


}
