package ru.dkalchenko.service;

import org.springframework.stereotype.Service;
import ru.dkalchenko.model.Product;
import ru.dkalchenko.repository.ProductDao;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public Product save(Product product) {
        Optional<Product> productOptional = productDao.save(product);
        return productOptional.orElseThrow(() -> new RuntimeException("Не удалось сохранить продукт"));
    }

    public void saveForUser(Long userId, Long productId) {
        productDao.saveForUser(userId, productId);
    }

    public Product getProductById(Long id) {
        Optional<Product> productOptional = productDao.findProductById(id);
        return productOptional.orElseThrow(() -> new RuntimeException("Не удалось найти продукт по id: " + id));
    }

    public List<Product> getProductsByUserId(Long id) {
        return productDao.findProductsByUserId(id);
    }

    public List<Product> findAll() {
        return productDao.findAll();
    }
}
