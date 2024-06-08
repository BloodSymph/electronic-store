package com.company.product.exception.handler;

import com.company.product.exception.entity.ErrorEntity;
import com.company.product.exception.exceptions.description.DescriptionNotFoundException;
import com.company.product.exception.exceptions.description.DescriptionVersionNotValidException;
import com.company.product.exception.exceptions.discount.DiscountNotFoundException;
import com.company.product.exception.exceptions.discount.DiscountVersionNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class DiscountExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DiscountNotFoundException.class)
    public ResponseEntity<ErrorEntity> productNotFoundException(
            DiscountNotFoundException discountNotFoundException) {
        ErrorEntity entity = new ErrorEntity();
        entity.setStatusCode(HttpStatus.NOT_FOUND.value());
        entity.setErrorMessage(discountNotFoundException.getMessage());
        entity.setErrorTimeStamp(new Date());
        return new ResponseEntity<>(entity, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DiscountVersionNotValidException.class)
    public ResponseEntity<ErrorEntity> productVersionNotValidException(
            DiscountVersionNotValidException discountVersionNotValidException) {
        ErrorEntity entity = new ErrorEntity();
        entity.setStatusCode(HttpStatus.BAD_REQUEST.value());
        entity.setErrorMessage(discountVersionNotValidException.getMessage());
        entity.setErrorTimeStamp(new Date());
        return new ResponseEntity<>(entity, HttpStatus.BAD_REQUEST);
    }
}
