package ru.dkalchenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dkalchenko.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
