package com.example.dailydo.exception.custom;

public class EmailAlreadyExistsException  extends RuntimeException{
    public EmailAlreadyExistsException(String email) {
        super("Email already exists: " + email);
    }
}
