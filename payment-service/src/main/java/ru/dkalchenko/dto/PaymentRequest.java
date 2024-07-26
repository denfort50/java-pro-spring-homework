package ru.dkalchenko.dto;

public record PaymentRequest(
        long userId,
        long productId,
        double sum) {
}
