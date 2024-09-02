package com.company.gateway.exception.exceptions.profile;

public class ProfileNotFoundException extends RuntimeException{

    public ProfileNotFoundException(String message) {
        super(message);
    }
}
