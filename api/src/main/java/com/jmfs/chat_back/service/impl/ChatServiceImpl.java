package com.jmfs.chat_back.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.jmfs.chat_back.domain.Chat;
import com.jmfs.chat_back.domain.MessageInfo;
import com.jmfs.chat_back.dto.ChatRequestDTO;
import com.jmfs.chat_back.dto.MessageDTO;
import com.jmfs.chat_back.exception.ChatNotFoundException;
import com.jmfs.chat_back.exception.MessagesNotFoundedException;
import com.jmfs.chat_back.repositories.ChatRepository;

import com.jmfs.chat_back.repositories.UserRepository;
import com.jmfs.chat_back.security.TokenService;
import com.jmfs.chat_back.service.ChatService;

@Service
public class ChatServiceImpl implements ChatService {
      private final UserRepository userRepository;
      private final ChatRepository chatRepository;
      private final SimpMessagingTemplate messagingTemplate;
      private final TokenService tokenService;

      @Autowired
      public ChatServiceImpl(UserRepository userRepository, ChatRepository chatRepository, SimpMessagingTemplate messagingTemplate, TokenService tokenService) {
            this.userRepository = userRepository;
            this.chatRepository = chatRepository;
            this.messagingTemplate = messagingTemplate;
            this.tokenService = tokenService;
      }

      /* 
       * metodo POST para pegar o chat caso existir ou criar um novo chat 
       * vai ser chamado sempre que um usuário clicar em algum chat
       * e deve salvar o id retornado no localstorage do front-end
      */

      @Override 
      public Long getChat(String token, Long userId) {

            Long userIdFromToken = tokenService.extractUserId(token);
            return chatRepository.findChatBetweenUsers(userIdFromToken, userId)
            .map(Chat::getId)
            .orElseGet(() -> {
                  Chat newChat = new Chat();
                  newChat.setUser_1(userRepository.findById(userIdFromToken)
                        .orElseThrow(() -> new ChatNotFoundException("Sender not found")));
                  newChat.setUser_2(userRepository.findById(userId)
                        .orElseThrow(() -> new ChatNotFoundException("Receiver not found")));
                  newChat.setMessages(new ArrayList<>());
                  return chatRepository.save(newChat).getId();
            });
      }
      
        /*
         * Metodo PUT para enviar uma mensagem
         * vai ser chamado sempre que um usuário enviar uma mensagem
         * e deve salvar a mensagem no chat correspondente
         */
      @Override 
      public MessageDTO sendMessage(ChatRequestDTO chatRequestDTO) {

            Chat chat = chatRepository.findChatBetweenUsers(chatRequestDTO.sender(), chatRequestDTO.receiver())
                        .orElseThrow(() -> new ChatNotFoundException("Chat not found"));

            MessageDTO message = new MessageDTO(chatRequestDTO.message(), 
                                                chatRequestDTO.sender(), 
                                                chat.getId(), 
                                                chatRequestDTO.timestamp());

            List<MessageDTO> messages = toMessageDTO(chat.getMessages(), chat.getId());
            if (messages == null) {
                  messages = new ArrayList<>();
            }
            messages.add(message);

            chat.setMessages(toMessageInfo(messages));
            boolean saved = chatRepository.save(chat) != null;

            if (saved){
                  userRepository.findById(chatRequestDTO.receiver())
                        .orElseThrow(() -> new ChatNotFoundException("Receiver not found"));
                  messagingTemplate.convertAndSendToUser(chatRequestDTO.receiver().toString(), "/queue/messages/", message);
                  return message;
            } else {
                  return null;
            }
      }
      
      /*
       * Metodo GET para pegar as mensagens de um usuário
       * vai ser chamado sempre que um usuário clicar em um chat
       * e deve retornar todas as mensagens do chat correspondente
       */
      @Override
      public List<MessageDTO> getMessages(Long chatId) {
            Chat chat = chatRepository.findById(chatId)
                        .orElseThrow(() -> new MessagesNotFoundedException("Messages not found for chat ID: " + chatId));
            return toMessageDTO(chat.getMessages(), chatId);
        }
        
      
        private List<MessageDTO> toMessageDTO(List<MessageInfo> messagesInfo, Long chatId) {
              List<MessageDTO> messages = new ArrayList<>();
              for (MessageInfo messageInfo : messagesInfo) {
                    messages.add(new MessageDTO(messageInfo.getContent(),
                                messageInfo.getUserId(),
                                chatId,
                                messageInfo.getTimestamp()));
              }
              return messages;
        }
      
      private List<MessageInfo> toMessageInfo(List<MessageDTO> messages) {
            List<MessageInfo> messageInfos = new ArrayList<>();
            for (MessageDTO message : messages) {
                  messageInfos.add(new MessageInfo( message.content(), 
                                                    message.userId(),
                                                    message.timestamp()));
            }
            return messageInfos;
      }
}
