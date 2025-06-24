package com.jmfs.chat_back.domain;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String username;

    private String password;

    private String email;

    
    @OneToMany(mappedBy = "user_1")
    private List<Chat> chatsAsUser1;

    @OneToMany(mappedBy = "user_2")
    private List<Chat> chatsAsUser2;

 
}
