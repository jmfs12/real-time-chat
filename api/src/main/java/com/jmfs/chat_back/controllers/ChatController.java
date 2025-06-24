package com.jmfs.chat_back.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jmfs.chat_back.dto.ChatRequestDTO;
import com.jmfs.chat_back.service.ChatService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

      private final ChatService chatService;

      /*
       * Método POST para 'entrar' em um chat.
       * Recebe um ChatRequestDTO contendo informações sobre o chat.
       * Retorna o ID do chat se encontrado, ou cria um novo chat se não existir
       * 
       */
      @PostMapping("/enter-chat")
      public ResponseEntity<Long> getChat(ChatRequestDTO chatRequestDTO) {
            Long chatId = chatService.getChat(chatRequestDTO);
            return ResponseEntity.ok(chatId); // OK
      }

      @PutMapping("/send-message")
      public ResponseEntity<Boolean> sendMessage(ChatRequestDTO chatRequestDTO) {
            if (chatService.sendMessage(chatRequestDTO)) {
                  return ResponseEntity.ok(true); // OK
            } else {
                  return ResponseEntity.badRequest().build(); // Bad Request
            }
      }
      
      @GetMapping("/messages/{chatId}/{userId}")
      public ResponseEntity<?> getMessagesFromUser(@PathVariable Long chatId, @PathVariable Long userId) {
            return null;
      }

}
