package com.company.review.exception.handler;

import com.company.review.exception.entity.ErrorEntity;
import com.company.review.exception.exceptions.ReviewNotFoundException;
import com.company.review.exception.exceptions.ReviewVersionNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class ReviewExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<ErrorEntity> reviewNotFoundExceptionHandler(
            ReviewNotFoundException reviewNotFoundException) {
        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorEntity.setErrorMessage(reviewNotFoundException.getMessage());
        errorEntity.setErrorTimeStamp(new Date());
        return new ResponseEntity<>(errorEntity, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ReviewVersionNotValidException.class)
    public ResponseEntity<ErrorEntity> reviewVersionNotValidExceptionHandler(
            ReviewVersionNotValidException reviewVersionNotValidException) {
        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorEntity.setErrorMessage(reviewVersionNotValidException.getMessage());
        errorEntity.setErrorTimeStamp(new Date());
        return new ResponseEntity<>(errorEntity, HttpStatus.BAD_REQUEST);
    }

}
