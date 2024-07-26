package ru.dkalchenko.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.dkalchenko.dto.ErrorResponse;
import ru.dkalchenko.exeption.ProductNotFoundException;
import ru.dkalchenko.exeption.TransactionImpossibleException;
import ru.dkalchenko.exeption.UserNotFoundException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse handleProductNotFoundException(ProductNotFoundException exception) {
        return new ErrorResponse(exception.getHttpStatus().value(), exception.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotFoundException(UserNotFoundException exception) {
        return new ErrorResponse(exception.getHttpStatus().value(), exception.getMessage());
    }

    @ExceptionHandler(TransactionImpossibleException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse handleTransactionImpossibleException(TransactionImpossibleException exception) {
        return new ErrorResponse(exception.getHttpStatus().value(), exception.getMessage());
    }
}
