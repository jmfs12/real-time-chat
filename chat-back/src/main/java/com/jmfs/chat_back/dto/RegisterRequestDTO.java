package com.jmfs.chat_back.dto;

public record RegisterRequestDTO(
        String username,
        String email,
        String password) {
}
