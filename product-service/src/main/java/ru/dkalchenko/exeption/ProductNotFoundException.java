package ru.dkalchenko.exeption;

import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends RuntimeException {

    private final HttpStatus httpStatus;

    public ProductNotFoundException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
