package com.jmfs.chat_back.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jmfs.chat_back.dto.ChatRequestDTO;
import com.jmfs.chat_back.dto.MessageDTO;
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
      @PostMapping("/enter")
      public ResponseEntity<Long> getChat(@RequestBody ChatRequestDTO chatRequestDTO) {
            Long chatId = chatService.getChat(chatRequestDTO);
            return ResponseEntity.ok(chatId); // OK
      }

      /*
       * Método GET para obter mensagens de um chat específico.
       * Recebe o ID do chat como parâmetro.
       * Retorna uma lista de mensagens do chat.
       */
      @GetMapping("/history/{chatId}")
      public ResponseEntity<List<MessageDTO>> getMessagesFromUser(@PathVariable Long chatId) {
            List<MessageDTO> messages = chatService.getMessages(chatId);
            if (messages.isEmpty()) {
                  return ResponseEntity.noContent().build(); // No Content
            } else {
                  return ResponseEntity.ok(messages); // OK
            }
      }

}
