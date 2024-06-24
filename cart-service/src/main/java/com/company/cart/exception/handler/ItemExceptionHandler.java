package com.company.cart.exception.handler;

import com.company.cart.exception.entity.ErrorEntity;
import com.company.cart.exception.exceptions.item.ItemNotFoundException;
import com.company.cart.exception.exceptions.item.ItemVersionNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class ItemExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ErrorEntity> itemNotFoundException(
            ItemNotFoundException itemNotFoundException) {

        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorEntity.setErrorMessage(itemNotFoundException.getMessage());
        errorEntity.setErrorTimeStamp(new Date());
        return new ResponseEntity<>(errorEntity, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ItemVersionNotValidException.class)
    public ResponseEntity<ErrorEntity> itemVersionException(
            ItemVersionNotValidException itemVersionNotValidException) {

        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorEntity.setErrorMessage(itemVersionNotValidException.getMessage());
        errorEntity.setErrorTimeStamp(new Date());
        return new ResponseEntity<>(errorEntity, HttpStatus.BAD_REQUEST);

    }

}
