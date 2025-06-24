package com.jmfs.chat_back.exception;

public class MessagesNotFoundedException extends RuntimeException {
      public MessagesNotFoundedException(String message) {
            super(message);
      }
}
