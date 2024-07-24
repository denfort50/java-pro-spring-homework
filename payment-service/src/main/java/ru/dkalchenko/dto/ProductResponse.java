package ru.dkalchenko.dto;

import ru.dkalchenko.model.Product;

import java.util.List;

public class ProductResponse {

    private List<Product> productList;

    public ProductResponse() {
    }

    public ProductResponse(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
