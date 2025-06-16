package com.jmfs.chat_back.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jmfs.chat_back.domain.user.User;
import com.jmfs.chat_back.dto.LoginRequestDTO;
import com.jmfs.chat_back.dto.ResponseDTO;
import com.jmfs.chat_back.infra.security.TokenService;
import com.jmfs.chat_back.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
      private final UserRepository userRepository;
      private final TokenService tokenService;
      private final PasswordEncoder passwordEncoder;

      @PostMapping("/login")
      public ResponseEntity<ResponseDTO> login(@RequestBody LoginRequestDTO body) {
            // Implement login logic here
            // Validate user credentials, generate token, etc.

            User user = this.userRepository.findByEmail(body.email())
                        .orElseThrow(() -> new RuntimeException("User not found"));

            if (passwordEncoder.matches(user.getPassword(), body.password())) {
                  String token = tokenService.generateToken(user);
                  return ResponseEntity.ok(new ResponseDTO(user.getUsername(), token) );
            } else {
                  return ResponseEntity.badRequest().build();
            }
                  
      }
}
