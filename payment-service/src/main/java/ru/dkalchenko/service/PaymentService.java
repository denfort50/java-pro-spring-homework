package ru.dkalchenko.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import ru.dkalchenko.dto.PaymentRequest;
import ru.dkalchenko.dto.PaymentResponse;
import ru.dkalchenko.exeption.TransactionImpossibleException;

@Service
public class PaymentService {

    private final RestClient restClient;
    private final ProductService productService;

    public PaymentService(RestClient restClient, ProductService productService) {
        this.restClient = restClient;
        this.productService = productService;
    }

    public PaymentResponse executePayment(PaymentRequest paymentRequest) {
        productService.getProductsByUserId(paymentRequest.userId());
        return restClient.post()
                .uri("/pay")
                .body(paymentRequest)
                .retrieve()
                .onStatus(status -> 404 == status.value(), (request, response) -> {
                    throw new TransactionImpossibleException("Сумма платежа превышает баланс", HttpStatus.NOT_FOUND);
                })
                .toEntity(PaymentResponse.class)
                .getBody();
    }
}
