package com.jmfs.chat_back.dto;

import java.time.LocalDateTime;

import com.jmfs.chat_back.domain.User;

public record ChatRequestDTO(String message, User sender, User receiver, LocalDateTime timestamp) {
    
    public ChatRequestDTO {
        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("Message cannot be null or blank");
        }
        if (sender == null) {
            throw new IllegalArgumentException("Sender cannot be null");
        }
        if (receiver == null) {
            throw new IllegalArgumentException("receiver cannot be null");
        }
    }
      
}
