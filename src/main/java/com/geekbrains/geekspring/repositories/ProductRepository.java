package com.geekbrains.geekspring.repositories;

import com.geekbrains.geekspring.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
