package com.company.cart.exception.handler;

import com.company.cart.exception.entity.ErrorEntity;
import com.company.cart.exception.exceptions.cart.CartNotFoundException;
import com.company.cart.exception.exceptions.cart.CartProfileIdNotValidException;
import com.company.cart.exception.exceptions.cart.CartVersionNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class CartExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<ErrorEntity> categoryNotFoundException(
            CartNotFoundException cartNotFoundException) {

        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorEntity.setErrorMessage(cartNotFoundException.getMessage());
        errorEntity.setErrorTimeStamp(new Date());
        return new ResponseEntity<>(errorEntity, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CartVersionNotValidException.class)
    public ResponseEntity<ErrorEntity> categoryVersionException(
            CartVersionNotValidException cartVersionNotValidException) {

        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorEntity.setErrorMessage(cartVersionNotValidException.getMessage());
        errorEntity.setErrorTimeStamp(new Date());
        return new ResponseEntity<>(errorEntity, HttpStatus.BAD_REQUEST);

    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CartProfileIdNotValidException.class)
    public ResponseEntity<ErrorEntity> categoryVersionException(
            CartProfileIdNotValidException cartProfileIdNotValidException) {

        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorEntity.setErrorMessage(cartProfileIdNotValidException.getMessage());
        errorEntity.setErrorTimeStamp(new Date());
        return new ResponseEntity<>(errorEntity, HttpStatus.BAD_REQUEST);

    }


}
