package com.jmfs.chat_back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jmfs.chat_back.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String login);
  Optional<User> findById(Long id);

  boolean existsByEmail(String email);

  
}