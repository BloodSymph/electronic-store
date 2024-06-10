package com.company.product.exception.handler;

import com.company.product.exception.entity.ErrorEntity;
import com.company.product.exception.exceptions.file.SearchingFileDirectoryException;
import com.company.product.exception.exceptions.product.ProductVersionNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class FileExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SearchingFileDirectoryException.class)
    public ResponseEntity<ErrorEntity> productVersionNotValidException(
            SearchingFileDirectoryException searchingFileDirectoryException) {
        ErrorEntity entity = new ErrorEntity();
        entity.setStatusCode(HttpStatus.BAD_REQUEST.value());
        entity.setErrorMessage(searchingFileDirectoryException.getMessage());
        entity.setErrorTimeStamp(new Date());
        return new ResponseEntity<>(entity, HttpStatus.BAD_REQUEST);
    }

}
