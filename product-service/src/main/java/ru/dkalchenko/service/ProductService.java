package ru.dkalchenko.service;

import org.springframework.stereotype.Service;
import ru.dkalchenko.model.Product;
import ru.dkalchenko.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product save(Product product) {
        Optional<Product> productOptional = productRepository.save(product);
        return productOptional.orElseThrow(() -> new RuntimeException("Не удалось сохранить продукт"));
    }

    public void saveForUser(Long userId, Long productId) {
        productRepository.saveForUser(userId, productId);
    }

    public Product getProductById(Long id) {
        Optional<Product> productOptional = productRepository.findProductById(id);
        return productOptional.orElseThrow(() -> new RuntimeException("Не удалось найти продукт по id: " + id));
    }

    public List<Product> getProductsByUserId(Long id) {
        return productRepository.findProductsByUserId(id);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
