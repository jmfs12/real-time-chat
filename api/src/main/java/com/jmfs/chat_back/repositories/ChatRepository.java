package com.jmfs.chat_back.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jmfs.chat_back.domain.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

      @Query("SELECT c FROM Chat c WHERE " +
      "(c.user_1.id = :user1 AND c.user_2.id = :user2) OR " +
      "(c.user_1.id = :user2 AND c.user_2.id = :user1)")
      Optional<Chat> findChatBetweenUsers(@Param("user1") Long user1, @Param("user2") Long user2);


      @Query("SELECT c FROM Chat c WHERE c.id = :chatId")
      Optional<Chat> findById(@Param("chatId") Long chatId);
      

}
