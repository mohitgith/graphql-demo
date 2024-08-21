package com.learning.graphqldemo.controller;

import com.learning.graphqldemo.entity.User;
import com.learning.graphqldemo.service.UserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;

import java.util.List;

public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @QueryMapping
    private List<User> getAllUsers() {
        return userService.getUsers();
    }

    @MutationMapping(name = "createUser")
    private User addUser(@Argument String username, @Argument String password, @Argument String email) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        return userService.createUser(user);
    }

    @SchemaMapping(typeName = "mutation", field = "deleteUser")
    private boolean deleteUserBuUserId(int userId) {
        return userService.deleteUserById(userId);
    }
}
