package com.jmfs.chat_back.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MessageInfo {
    private String content;
    private Long userId;
    private String timestamp;
}