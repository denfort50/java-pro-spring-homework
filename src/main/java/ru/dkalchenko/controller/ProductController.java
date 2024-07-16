package ru.dkalchenko.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.dkalchenko.dto.ProductResponse;
import ru.dkalchenko.model.Product;
import ru.dkalchenko.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getByProductId")
    public Product getProductById(@RequestParam(value = "productId") long productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("getByUserId")
    public ProductResponse getProductsByUserId(@RequestParam(value = "userId") long userId) {
        return new ProductResponse(productService.getProductsByUserId(userId));
    }
}
