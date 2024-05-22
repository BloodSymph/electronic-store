package com.company.product.exception.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ToString
public class ErrorEntity {

    private Integer statusCode;

    @NotNull(message = "Error message shod not contains null value!")
    @NotBlank(message = "Error message shod not be empty!")
    private String errorMessage;

    @DateTimeFormat(pattern = "E, dd MMM yyyy HH:mm:ss z")
    private Date errorTimeStamp;

}
