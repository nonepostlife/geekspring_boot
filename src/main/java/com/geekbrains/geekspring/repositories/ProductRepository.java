package com.geekbrains.geekspring.repositories;

import com.geekbrains.geekspring.entities.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {


}
