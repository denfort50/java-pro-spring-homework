package ru.dkalchenko.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.dkalchenko.dto.PaymentRequest;
import ru.dkalchenko.dto.PaymentResponse;
import ru.dkalchenko.exeption.ProductNotFoundException;
import ru.dkalchenko.exeption.TransactionImpossibleException;
import ru.dkalchenko.model.Product;
import ru.dkalchenko.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final UserService userService;

    public ProductService(ProductRepository productRepository, UserService userService) {
        this.productRepository = productRepository;
        this.userService = userService;
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void update(Product product) {
        save(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() ->
                new ProductNotFoundException("Не удалось найти продукт по id: " + id, HttpStatus.NOT_FOUND));
    }

    public List<Product> getProductsByUserId(Long id) {
        return userService.findUserWithProducts(id).getProducts();
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public PaymentResponse handlePaymentRequest(PaymentRequest request) {
        List<Product> products = getProductsByUserId(request.userId());
        Product userProduct = products.stream()
                .filter(product -> request.productId() == product.getId())
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("У пользователя с ID = " + request.userId()
                        + " нет продукта с ID = " + request.productId(), HttpStatus.NOT_FOUND));
        double balance = userProduct.getBalance();
        if (request.sum() > balance) {
            throw new TransactionImpossibleException("Сумма платежа превышает баланс", HttpStatus.CONFLICT);
        } else {
            userProduct.setBalance(balance - request.sum());
            this.update(userProduct);
            return new PaymentResponse("Платеж успешно проведен");
        }
    }
}
