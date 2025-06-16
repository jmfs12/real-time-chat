package com.jmfs.chat_back.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jmfs.chat_back.dto.LoginRequestDTO;
import com.jmfs.chat_back.dto.ResponseDTO;
import com.jmfs.chat_back.service.AuthService;
import com.jmfs.chat_back.dto.RegisterRequestDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
      private final AuthService authService;


      @PostMapping("/login")
      public ResponseEntity<ResponseDTO> login(@RequestBody LoginRequestDTO body) {
            // Implement login logic here
            // Validate user credentials, generate token, etc.
            log.info("[AUTH] Login attempt for user: {}", body.email());
            if (body.email() == null || body.password() == null) {
                  log.warn("[AUTH] Login failed due to missing credentials for user: {}", body.email());
                  return ResponseEntity.badRequest().build(); // Bad Request
            }
            ResponseDTO response = authService.login(body);
            if (response == null) {
                  log.warn("[AUTH] Login failed for user: {}", body.email());
                  return ResponseEntity.status(401).build(); // Unauthorized
            }
            log.info("[AUTH] User {} logged in successfully", response.name());
            return ResponseEntity.ok(response);
            
      }

      @PostMapping("/register")
      public ResponseEntity<ResponseDTO> register(@RequestBody RegisterRequestDTO body) {
            // Implement registration logic here
            // Validate input, check if user already exists, save user, etc.
            log.info("[AUTH] Registration attempt for user: {}", body.email());
            if (body.email() == null || body.username() == null || body.password() == null) {
                  log.warn("[AUTH] Registration failed due to missing credentials for user: {}", body.email());
                  return ResponseEntity.badRequest().build(); // Bad Request
            }
            ResponseDTO response = authService.register(body);
            if (response == null) {
                  log.warn("[AUTH] Registration failed for user: {}", body.email());
                  return ResponseEntity.status(400).build(); // Bad Request
            }
            log.info("[AUTH] User {} registered successfully", response.name());
            return ResponseEntity.ok(response);
      }
}
