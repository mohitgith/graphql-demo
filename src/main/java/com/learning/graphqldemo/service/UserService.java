package com.learning.graphqldemo.service;

import com.learning.graphqldemo.entity.User;
import com.learning.graphqldemo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        List<User> allUsers = (List<User>) userRepository.findAll();
        return allUsers;
    }

    public User createUser(User user) {
        if (!userRepository.existsById(user.getUserId())) {
            user.setUsername(user.getUsername());
            user.setPassword(user.getPassword());
            user.setEmail(user.getEmail());
        }
        return userRepository.save(user);
    }

    public User getUserByUserId(int userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.orElseThrow(() -> new RuntimeException());
        return user;
    }

    public boolean deleteUserById(int userId) {
        User existingUser = getUserByUserId(userId);
        userRepository.delete(existingUser);
        return true;
    }
}
