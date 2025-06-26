package com.jmfs.chat_back.domain;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "chats")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Chat {

      @Id
      @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
      private Long id;

      @ManyToOne
      @JoinColumn(name = "user_1_id", nullable = false)
      private User user_1;

      @ManyToOne
      @JoinColumn(name = "user_2_id", nullable = false)
      private User user_2;

      @ElementCollection
      private List<MessageInfo> messages;

}
