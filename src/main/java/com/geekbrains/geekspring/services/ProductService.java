package com.geekbrains.geekspring.services;

import com.geekbrains.geekspring.entities.Product;
import com.geekbrains.geekspring.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProductList() {
        return (List<Product>) productRepository.findAll();
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
