package com.company.order.exception.handler;

import com.company.order.exception.entity.ErrorEntity;
import com.company.order.exception.exceptions.OrderNotFoundException;
import com.company.order.exception.exceptions.OrderVersionNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class OrderExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorEntity> orderNotFoundExceptionHandler(
            OrderNotFoundException orderNotFoundException) {
        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorEntity.setErrorMessage(orderNotFoundException.getMessage());
        errorEntity.setErrorTimeStamp(new Date());
        return new ResponseEntity<>(errorEntity, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OrderVersionNotValidException.class)
    public ResponseEntity<ErrorEntity> orderVersionNotValidExceptionHandler(
            OrderVersionNotValidException orderVersionNotValidException) {
        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorEntity.setErrorMessage(orderVersionNotValidException.getMessage());
        errorEntity.setErrorTimeStamp(new Date());
        return new ResponseEntity<>(errorEntity, HttpStatus.BAD_REQUEST);
    }

}
