package com.jmfs.chat_back.service;

import java.util.List;

import com.jmfs.chat_back.dto.ChatRequestDTO;
import com.jmfs.chat_back.dto.MessageDTO;

public interface ChatService {
      public MessageDTO sendMessage(ChatRequestDTO chatRequestDTO);

      public Long getChat(ChatRequestDTO chatRequestDTO);

      public List<MessageDTO> getMessages(Long chatId);

}
