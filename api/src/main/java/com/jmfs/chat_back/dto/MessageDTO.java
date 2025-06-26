package com.jmfs.chat_back.dto;

public record MessageDTO(String content, Long userId, Long chatId, String timestamp) {

    public MessageDTO(String content, Long userId, Long chatId, String timestamp) {
      this.content = content;
      this.userId = userId;
      this.chatId = chatId;
      this.timestamp = timestamp;
    }
      
}
