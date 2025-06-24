package com.jmfs.chat_back.service;

import java.util.List;

import com.jmfs.chat_back.domain.Message;
import com.jmfs.chat_back.dto.ChatRequestDTO;
import com.jmfs.chat_back.dto.MessageRequestDTO;

public interface ChatService {
      public Boolean sendMessage(ChatRequestDTO chatRequestDTO);

      public Long getChat(ChatRequestDTO chatRequestDTO);

      public List<Message> getMessagesFromUser(MessageRequestDTO messageRequestDTO);

}
