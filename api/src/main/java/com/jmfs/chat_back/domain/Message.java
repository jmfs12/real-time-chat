package com.jmfs.chat_back.domain;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Message {

      @Id
      @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
      private Long id;

      @ManyToOne
      @JoinColumn(name = "chat_id", nullable = false)
      @JsonBackReference  
      private Chat chat;

      @ManyToOne
      @JoinColumn(name = "sender_id", nullable = false)
      private User sender;
      @ManyToOne
      @JoinColumn(name = "receiver_id", nullable = false)
      private User receiver;

      private String content;

      private LocalDateTime timestamp;
      
}
