package ru.dkalchenko.service;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import ru.dkalchenko.dto.PaymentRequest;
import ru.dkalchenko.dto.PaymentResponse;
import ru.dkalchenko.exeption.TransactionImpossibleException;
import ru.dkalchenko.model.Product;
import ru.dkalchenko.model.ProductType;
import ru.dkalchenko.model.User;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
@Sql({"/sql/users.sql", "/sql/products.sql"})
@Transactional
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void whenGetProductsByUserIdThenSuccess() {
        User user = User.builder()
                .username("Alex")
                .build();
        User userInDb = userService.save(user);
        Product product1 = Product.builder()
                .accountNumber(420500679746848056L)
                .balance(14006.49)
                .productType(ProductType.ACCOUNT)
                .user(userInDb)
                .build();
        Product product2 = Product.builder()
                .accountNumber(220200879846948356L)
                .balance(500000.54)
                .productType(ProductType.ACCOUNT)
                .user(userInDb)
                .build();
        productService.save(product1);
        productService.save(product2);
        entityManager.clear();
        List<Product> products = productService.getProductsByUserId(userInDb.getId());
        assertThat(products).hasSize(2);
    }

    @Test
    public void whenHandlePaymentRequestThenSuccess() {
        User user = User.builder()
                .username("Alex")
                .build();
        User userInDb = userService.save(user);
        Product product = Product.builder()
                .accountNumber(420500679746848056L)
                .balance(14006.49)
                .productType(ProductType.ACCOUNT)
                .user(userInDb)
                .build();
        Product productInDb = productService.save(product);
        PaymentRequest paymentRequest = new PaymentRequest(userInDb.getId(), productInDb.getId(), 10000L);
        entityManager.clear();
        PaymentResponse paymentResponse = productService.handlePaymentRequest(paymentRequest);
        assertThat(paymentResponse.message()).isEqualTo("Платеж успешно проведен");
        assertThat(productService.getProductById(productInDb.getId()).getBalance()).isEqualTo(4006.49);
    }

    @Test
    public void whenHandlePaymentRequestThenFail() {
        User user = User.builder()
                .username("Alex")
                .build();
        User userInDb = userService.save(user);
        Product product = Product.builder()
                .accountNumber(420500679746848056L)
                .balance(14006.49)
                .productType(ProductType.ACCOUNT)
                .user(userInDb)
                .build();
        Product productInDb = productService.save(product);
        PaymentRequest paymentRequest = new PaymentRequest(userInDb.getId(), productInDb.getId(), 20000L);
        entityManager.clear();
        assertThrows(TransactionImpossibleException.class, () -> productService.handlePaymentRequest(paymentRequest),
                "Сумма платежа превышает баланс");
    }
}
