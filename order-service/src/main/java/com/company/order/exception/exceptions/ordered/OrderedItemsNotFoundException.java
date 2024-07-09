package com.company.order.exception.exceptions.ordered;

public class OrderedItemsNotFoundException extends RuntimeException{

    public OrderedItemsNotFoundException(String message) {
        super(message);
    }
}
