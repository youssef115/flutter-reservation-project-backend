package com.youssef.flutterProjectBackend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private Long cin;
    private Role role;
}
