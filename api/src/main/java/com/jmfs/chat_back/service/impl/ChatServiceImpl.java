package com.jmfs.chat_back.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jmfs.chat_back.domain.Chat;
import com.jmfs.chat_back.domain.Message;
import com.jmfs.chat_back.dto.ChatRequestDTO;
import com.jmfs.chat_back.dto.MessageRequestDTO;
import com.jmfs.chat_back.exception.ChatNotFoundException;
import com.jmfs.chat_back.exception.MessagesNotFoundedException;
import com.jmfs.chat_back.repositories.ChatRepository;
import com.jmfs.chat_back.repositories.MessageRepository;
import com.jmfs.chat_back.repositories.UserRepository;
import com.jmfs.chat_back.service.ChatService;

@Service
public class ChatServiceImpl implements ChatService {
      private final UserRepository userRepository;
      private final ChatRepository chatRepository;
      private final MessageRepository messageRepository;

      @Autowired
      public ChatServiceImpl(UserRepository userRepository, ChatRepository chatRepository, MessageRepository messageRepository) {
            this.userRepository = userRepository;
            this.chatRepository = chatRepository;
            this.messageRepository = messageRepository;
      }

      /* 
       * metodo POST para pegar o chat caso existir ou criar um novo chat 
       * vai ser chamado sempre que um usuário clicar em algum chat
       * e deve salvar o id retornado no localstorage do front-end
      */
      @Override 
      public Long getChat(ChatRequestDTO chatRequestDTO) {
            return chatRepository.findChatBetweenUsers(chatRequestDTO.sender(), chatRequestDTO.receiver())
                .map(Chat::getId)
                .orElseGet(() -> {
                    Chat newChat = new Chat();
                    newChat.setUser_1(chatRequestDTO.sender());
                    newChat.setUser_2(chatRequestDTO.receiver());
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
      public Boolean sendMessage(ChatRequestDTO chatRequestDTO) {

            Chat chat = chatRepository.findChatBetweenUsers(chatRequestDTO.sender(), chatRequestDTO.receiver())
                        .orElseThrow(() -> new ChatNotFoundException("Chat not found"));

            Message message = new Message();
            message.setSender(chatRequestDTO.sender());
            message.setReceiver(chatRequestDTO.receiver());
            message.setContent(chatRequestDTO.message());
            message.setTimestamp(chatRequestDTO.timestamp());
            message.setChat(chat);

            List<Message> messages = chat.getMessages();
            if (messages == null) {
                  messages = new ArrayList<>();
            }
            messages.add(message);

            chat.setMessages(messages);
            return chatRepository.save(chat) != null;

      }
      
      /*
       * Metodo GET para pegar as mensagens de um usuário
       * vai ser chamado sempre que um usuário clicar em um chat
       * e deve retornar todas as mensagens do chat correspondente
       */
      @Override
      public List<Message> getMessagesFromUser(MessageRequestDTO messageRequestDTO) {
            return messageRepository.findMessagesByUserIdAndChatId(
                  messageRequestDTO.chatId(), 
                  messageRequestDTO.userId()
            ).orElseThrow(() -> new MessagesNotFoundedException("No messages found for the given chat and sender"));
      }
      
}
