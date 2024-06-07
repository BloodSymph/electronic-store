package com.company.product.exception.handler;

import com.company.product.exception.entity.ErrorEntity;
import com.company.product.exception.exceptions.product.ProductNotFoundException;
import com.company.product.exception.exceptions.product.ProductVersionNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class ProductExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorEntity> productNotFoundException(
            ProductNotFoundException productNotFoundException) {
        ErrorEntity entity = new ErrorEntity();
        entity.setStatusCode(HttpStatus.NOT_FOUND.value());
        entity.setErrorMessage(productNotFoundException.getMessage());
        entity.setErrorTimeStamp(new Date());
        return new ResponseEntity<>(entity, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductVersionNotValidException.class)
    public ResponseEntity<ErrorEntity> productVersionNotValidException(
            ProductVersionNotValidException productVersionNotValidException) {
        ErrorEntity entity = new ErrorEntity();
        entity.setStatusCode(HttpStatus.BAD_REQUEST.value());
        entity.setErrorMessage(productVersionNotValidException.getMessage());
        entity.setErrorTimeStamp(new Date());
        return new ResponseEntity<>(entity, HttpStatus.BAD_REQUEST);
    }

}
