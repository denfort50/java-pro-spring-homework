package ru.dkalchenko.exeption;

import org.springframework.http.HttpStatus;

public class TransactionImpossibleException extends RuntimeException {

    private final HttpStatus httpStatus;

    public TransactionImpossibleException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
