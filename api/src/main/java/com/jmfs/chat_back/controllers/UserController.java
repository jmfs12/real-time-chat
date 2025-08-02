package com.jmfs.chat_back.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jmfs.chat_back.dto.ChatDTO;
import com.jmfs.chat_back.dto.UserDTO;
import com.jmfs.chat_back.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getUser(@RequestHeader("Authorization") String token) {
        UserDTO userDTO = userService.getUser(token.substring(7));
        if (userDTO == null) {
            return ResponseEntity.status(401).build(); // Unauthorized
        }
        return ResponseEntity.ok(userDTO); // OK
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> userDTOs = userService.getAllUsers();
        if (userDTOs == null) {
            return ResponseEntity.status(401).build(); // Unauthorized
        }
        return ResponseEntity.ok(userDTOs); // OK
    }

    @GetMapping("/chats")
    public ResponseEntity<List<ChatDTO>> getUserChats(@RequestHeader("Authorization") String token) {
        List<ChatDTO> chatDTOs = userService.getUserChats(token.substring(7));
        if (chatDTOs == null) {
            return ResponseEntity.status(401).build(); // Unauthorized
        }
        return ResponseEntity.ok(chatDTOs); // OK
    }

}

