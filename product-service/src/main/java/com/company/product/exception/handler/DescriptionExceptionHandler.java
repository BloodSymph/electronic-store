package com.company.product.exception.handler;

import com.company.product.exception.entity.ErrorEntity;
import com.company.product.exception.exceptions.description.DescriptionNotFoundException;
import com.company.product.exception.exceptions.description.DescriptionVersionNotValidException;
import com.company.product.exception.exceptions.product.ProductNotFoundException;
import com.company.product.exception.exceptions.product.ProductVersionNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class DescriptionExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DescriptionNotFoundException.class)
    public ResponseEntity<ErrorEntity> productNotFoundException(
            DescriptionNotFoundException descriptionNotFoundException) {
        ErrorEntity entity = new ErrorEntity();
        entity.setStatusCode(HttpStatus.NOT_FOUND.value());
        entity.setErrorMessage(descriptionNotFoundException.getMessage());
        entity.setErrorTimeStamp(new Date());
        return new ResponseEntity<>(entity, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DescriptionVersionNotValidException.class)
    public ResponseEntity<ErrorEntity> productVersionNotValidException(
            DescriptionVersionNotValidException descriptionVersionNotValidException) {
        ErrorEntity entity = new ErrorEntity();
        entity.setStatusCode(HttpStatus.BAD_REQUEST.value());
        entity.setErrorMessage(descriptionVersionNotValidException.getMessage());
        entity.setErrorTimeStamp(new Date());
        return new ResponseEntity<>(entity, HttpStatus.BAD_REQUEST);
    }


}


