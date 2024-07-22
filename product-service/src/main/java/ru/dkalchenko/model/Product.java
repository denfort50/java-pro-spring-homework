package ru.dkalchenko.model;

import java.util.Objects;

public class Product {

    private Long id;
    private Long accountNumber;
    private Double balance;
    private ProductType productType;

    public Product(Long id, Long accountNumber, Double balance, ProductType productType) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.productType = productType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        return Objects.equals(id, product.id)
                && Objects.equals(accountNumber, product.accountNumber)
                && Objects.equals(balance, product.balance)
                && productType == product.productType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountNumber, balance, productType);
    }

    @Override
    public String toString() {
        return "Product{"
                + "id=" + id
                + ", accountNumber=" + accountNumber
                + ", balance=" + balance
                + ", productType=" + productType
                + '}';
    }
}
