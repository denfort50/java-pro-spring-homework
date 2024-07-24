package ru.dkalchenko.controller;

import org.springframework.web.bind.annotation.*;
import ru.dkalchenko.dto.PaymentRequest;
import ru.dkalchenko.dto.PaymentResponse;
import ru.dkalchenko.dto.ProductResponse;
import ru.dkalchenko.model.Product;
import ru.dkalchenko.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/one")
    public Product getProductById(@RequestParam(value = "productId") long productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("/all")
    public ProductResponse getProductsByUserId(@RequestParam(value = "userId") long userId) {
        return new ProductResponse(productService.getProductsByUserId(userId));
    }

    @PostMapping("/pay")
    public PaymentResponse handlePaymentRequest(@RequestBody PaymentRequest request) {
        return productService.handlePaymentRequest(request);
    }
}
