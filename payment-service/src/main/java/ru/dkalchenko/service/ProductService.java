package ru.dkalchenko.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import ru.dkalchenko.dto.ProductResponse;
import ru.dkalchenko.exeption.UserNotFoundException;

@Service
public class ProductService {

    private final RestClient restClient;

    public ProductService(RestClient restClient) {
        this.restClient = restClient;
    }

    public ProductResponse getProductsByUserId(long userId) {
        return restClient.get()
                .uri("/all?userId={userId}", userId)
                .retrieve()
                .onStatus(status -> 404 == status.value(), (request, response) -> {
                    throw new UserNotFoundException("Не удалось найти пользователя по id: " + userId,
                            HttpStatus.NOT_FOUND);
                })
                .toEntity(ProductResponse.class)
                .getBody();
    }

}
