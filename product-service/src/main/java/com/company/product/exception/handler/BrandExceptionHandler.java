package com.company.product.exception.handler;

import com.company.product.exception.entity.ErrorEntity;
import com.company.product.exception.exceptions.brand.BrandNotFoundException;
import com.company.product.exception.exceptions.brand.BrandVersionNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class BrandExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BrandNotFoundException.class)
    public ResponseEntity<ErrorEntity> brandNotFoundException(
            BrandNotFoundException brandNotFoundException) {

        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorEntity.setErrorMessage(brandNotFoundException.getMessage());
        errorEntity.setErrorTimeStamp(new Date());

        return new ResponseEntity<>(errorEntity, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BrandVersionNotValidException.class)
    public ResponseEntity<ErrorEntity> brandVersionException(
            BrandVersionNotValidException brandVersionNotValidException) {

        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorEntity.setErrorMessage(brandVersionNotValidException.getMessage());
        errorEntity.setErrorTimeStamp(new Date());

        return new ResponseEntity<>(errorEntity, HttpStatus.BAD_REQUEST);
    }

}
