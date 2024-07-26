package ru.dkalchenko.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.dkalchenko.dto.ProductResponse;
import ru.dkalchenko.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ProductResponse getProductsById(@RequestParam(value = "userId") long userId) {
        return productService.getProductsByUserId(userId);
    }
}
