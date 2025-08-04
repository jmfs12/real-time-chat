package com.jmfs.chat_back.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jmfs.chat_back.domain.User;
import com.jmfs.chat_back.dto.ChatDTO;
import com.jmfs.chat_back.dto.UserDTO;
import com.jmfs.chat_back.repositories.UserRepository;
import com.jmfs.chat_back.security.TokenService;
import com.jmfs.chat_back.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private TokenService tokenService;

    public UserServiceImpl(UserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @Override
    public UserDTO getUser(String token) {

        Long userId = tokenService.extractUserId(token);
        if (userId == null) {
            return null;
        }

        return userRepository.findById(userId)
                .map(user -> new UserDTO(user.getId(), user.getUsername(), user.getEmail()))
                .orElse(null);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        // Logic to validate token and return all users
        return userRepository.findAll().stream()
                .map(user -> new UserDTO(user.getId(), user.getUsername(), user.getEmail()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ChatDTO> getUserChats(String token) {
        Long userId = tokenService.extractUserId(token);
        if (userId == null) {
            return null;
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getChatsAsUser1()
            .stream()
            .map(chat -> new ChatDTO(chat.getId(), chat.getUser_1().getId(), chat.getUser_2().getId()))
            .collect(Collectors.toList());
    }
}
