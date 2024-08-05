package ru.dkalchenko.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import ru.dkalchenko.model.Product;
import ru.dkalchenko.model.ProductType;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@Sql({"/sql/users.sql", "/sql/products.sql"})
@Transactional
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void whenSaveThenSuccess() {
        Product product = Product.builder()
                .accountNumber(220200879846948356L)
                .balance(500000.54)
                .productType(ProductType.ACCOUNT)
                .build();
        Product savedProduct = productRepository.save(product);
        assertThat(savedProduct.getId()).isNotNull();
    }

    @Test
    public void whenGetProductByIdThenSuccess() {
        Product product = Product.builder()
                .accountNumber(220200879846948356L)
                .balance(500000.54)
                .productType(ProductType.ACCOUNT)
                .build();
        Product savedProduct = productRepository.save(product);
        Optional<Product> productInDb = productRepository.findById(savedProduct.getId());
        assertThat(productInDb).isNotEmpty();
    }

    @Test
    public void whenGetProductByIdThenFail() {
        Product product = Product.builder()
                .accountNumber(220200879846948356L)
                .balance(500000.54)
                .productType(ProductType.ACCOUNT)
                .build();
        Product savedProduct = productRepository.save(product);
        Optional<Product> productInDb = productRepository.findById(10L);
        assertThat(productInDb).isEmpty();
    }

    @Test
    public void whenFindAllThenSuccess() {
        Product product1 = Product.builder()
                .accountNumber(420500679746848056L)
                .balance(14006.49)
                .productType(ProductType.ACCOUNT)
                .build();
        Product product2 = Product.builder()
                .accountNumber(220200879846948356L)
                .balance(500000.54)
                .productType(ProductType.ACCOUNT)
                .build();
        productRepository.save(product1);
        productRepository.save(product2);
        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(2);
    }

}
