package com.jmfs.chat_back.dto;

public record ChatRequestDTO(String message, Long sender, Long receiver, String timestamp) {
      
}
