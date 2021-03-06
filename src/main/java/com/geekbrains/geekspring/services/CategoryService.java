package com.geekbrains.geekspring.services;

import com.geekbrains.geekspring.entities.Category;
import com.geekbrains.geekspring.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategoryList() {
        return categoryRepository.findAllByParentIdIsNotNull();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).get();
    }
}
