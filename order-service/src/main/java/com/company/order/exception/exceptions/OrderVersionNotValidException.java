package com.company.order.exception.exceptions;

public class OrderVersionNotValidException extends RuntimeException{

    public OrderVersionNotValidException(String message) {
        super(message);
    }
}
