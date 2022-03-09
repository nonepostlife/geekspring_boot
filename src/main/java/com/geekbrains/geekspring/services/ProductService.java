package com.geekbrains.geekspring.services;

import com.geekbrains.geekspring.entities.Product;
import com.geekbrains.geekspring.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> getAllProductList(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Product> pagedResult = productRepository.findAll(paging);
        return pagedResult;
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).get();
    }

    public void addProduct(Product p) {
        productRepository.save(p);
    }

    public void removeById(Long id) {
        productRepository.deleteById(id);
    }
}
