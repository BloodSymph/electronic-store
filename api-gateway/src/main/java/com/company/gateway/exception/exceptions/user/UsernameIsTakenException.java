package com.company.gateway.exception.exceptions.user;

public class UsernameIsTakenException extends RuntimeException{

    public UsernameIsTakenException(String message) {
        super(message);
    }
}
