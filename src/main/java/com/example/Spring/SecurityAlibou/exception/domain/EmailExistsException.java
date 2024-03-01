package com.example.Spring.SecurityAlibou.exception.domain;

public class EmailExistsException extends Exception {

    public EmailExistsException(String message) {
        super(message);
    }
}
