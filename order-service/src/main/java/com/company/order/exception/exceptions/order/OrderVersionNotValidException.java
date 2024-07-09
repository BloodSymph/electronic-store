package com.company.order.exception.exceptions.order;

public class OrderVersionNotValidException extends RuntimeException{

    public OrderVersionNotValidException(String message) {
        super(message);
    }
}
