package com.hendro.auth;

import lombok.*;

@Getter
@Setter
@Builder
public class AuthenticationResponse {
    private String token;
}
