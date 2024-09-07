package com.hendro.user;

public record UserResponse(
        String id,
        String username,
        String password,
        String email
) {
}
