package com.jmfs.chat_back.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.jmfs.chat_back.dto.ChatRequestDTO;
import com.jmfs.chat_back.dto.MessageDTO;
import com.jmfs.chat_back.service.ChatService;

@Controller
public class LiveChatController {
    private final ChatService chatService;

    public LiveChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @MessageMapping("/chat.send")
    public MessageDTO sendMessage(ChatRequestDTO chatRequestDTO) {
        return chatService.sendMessage(chatRequestDTO);
    }
}