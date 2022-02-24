package com.geekbrains.geekspring.repositories;

import com.geekbrains.geekspring.entities.Category;
import com.geekbrains.geekspring.entities.Product;
import com.geekbrains.geekspring.entities.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    List<Category> findAllByParentIdIsNotNull();
}
