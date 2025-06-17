package com.jmfs.chat_back.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jmfs.chat_back.domain.User;
import com.jmfs.chat_back.dto.LoginRequestDTO;
import com.jmfs.chat_back.dto.ResponseDTO;
import com.jmfs.chat_back.repositories.UserRepository;
import com.jmfs.chat_back.security.TokenService;
import com.jmfs.chat_back.service.AuthService;

import lombok.extern.slf4j.Slf4j;

import com.jmfs.chat_back.dto.RegisterRequestDTO;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
      private final UserRepository userRepository;
      private final TokenService tokenService;
      private final PasswordEncoder passwordEncoder;

      @Autowired
      public AuthServiceImpl(UserRepository userRepository, TokenService tokenService, PasswordEncoder passwordEncoder) {
            this.userRepository = userRepository;
            this.tokenService = tokenService;
            this.passwordEncoder = passwordEncoder;
      }

      @Override
      public ResponseDTO login(LoginRequestDTO body) {
            log.info("[LOGIN] Attempting to login user with email: {}", body.email());
            User user = this.userRepository.findByEmail(body.email())
                        .orElseThrow(() -> {
                              log.warn("[LOGIN] User not found with email: {}", body.email());
                              return new RuntimeException("User not found");
                        });

            if (passwordEncoder.matches(body.password(), user.getPassword())) {
                  String token = tokenService.generateToken(user);
                  log.info("[LOGIN] User {} logged in successfully", user.getEmail());
                  return new ResponseDTO(user.getUsername(), token);
            } else {
                  log.warn("[LOGIN] Invalid password for user: {}", body.email());
                  return null;
            }
      }
      
      @Override
      public ResponseDTO register(RegisterRequestDTO body) {
            log.info("[REGISTER] Attempting to register user with email: {}", body.email());
            if (userRepository.existsByEmail(body.email())) {
                  log.warn("[REGISTER] User already exists with email: {}", body.email());
                  return null; // User already exists
            }

            User newUser = new User();
            newUser.setUsername(body.username());
            newUser.setEmail(body.email());
            newUser.setPassword(passwordEncoder.encode(body.password()));

            userRepository.save(newUser);

            String token = tokenService.generateToken(newUser);
            log.info("[REGISTER] User {} registered successfully", newUser.getEmail());
            return new ResponseDTO(newUser.getUsername(), token);
      }
}
