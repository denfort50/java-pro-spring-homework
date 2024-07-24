package ru.dkalchenko.controller;

import org.springframework.web.bind.annotation.*;
import ru.dkalchenko.dto.PaymentRequest;
import ru.dkalchenko.dto.PaymentResponse;
import ru.dkalchenko.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/execute")
    public PaymentResponse executePayment(@RequestBody PaymentRequest request) {
        return paymentService.executePayment(request);
    }
}
