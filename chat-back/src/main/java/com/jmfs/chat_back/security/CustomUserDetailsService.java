package com.jmfs.chat_back.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.jmfs.chat_back.domain.User;
import com.jmfs.chat_back.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
      
      @Autowired
      private UserRepository userRepository;

      @Override
      public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            log.info("[USER DETAILS] Loading user by username: {}", username);
            User user = userRepository.findByEmail(username)
                        .orElseThrow(() -> {
                              log.warn("[USER DETAILS] User not found with email: {}", username);
                              return new UsernameNotFoundException("User not found with email: " + username);
                        });
            log.info("[USER DETAILS] User found: {}", user.getEmail());
            return new org.springframework.security.core.userdetails.User(
                        user.getEmail(),
                        user.getPassword(),
                        new ArrayList<>()
            );
      }
}
