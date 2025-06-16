package com.jmfs.chat_back.service;

import com.jmfs.chat_back.dto.LoginRequestDTO;
import com.jmfs.chat_back.dto.RegisterRequestDTO;
import com.jmfs.chat_back.dto.ResponseDTO;

public interface AuthService {
    ResponseDTO login(LoginRequestDTO body);
    ResponseDTO register(RegisterRequestDTO body);
}
