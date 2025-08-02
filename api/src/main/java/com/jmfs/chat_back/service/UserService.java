package com.jmfs.chat_back.service;

import java.util.List;

import com.jmfs.chat_back.dto.ChatDTO;
import com.jmfs.chat_back.dto.UserDTO;

public interface UserService {
    UserDTO getUser(String token);
    List<UserDTO> getAllUsers();
    List<ChatDTO> getUserChats(String token); // Method to get user's chats
}
