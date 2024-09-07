package com.hendro.user;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public static User toUser(UserRequest request, String encodedPassword) {
        User user = new User();
        user.setUsername(request.username());
        user.setPassword(encodedPassword);
        return user;
    }
}
