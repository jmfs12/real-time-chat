package com.jmfs.chat_back.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jmfs.chat_back.domain.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{
      
      @Query("SELECT m FROM Message m WHERE m.sender.id = :userId AND m.chat.id = :chatId")
      Optional<List<Message>> findMessagesByUserIdAndChatId(@Param("userId") Long userId, 
                                                  @Param("chatId") Long chatId);

}
