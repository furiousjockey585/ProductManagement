package com.app.repository;

import com.app.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    void addCategory(Category category);
    void updateCategory(Category category);
    void deleteCategory(Integer id);
    Optional<Category> findOneById(Integer id);
    Optional<Category> findOneByName(String name);
    List<Category> findAll();
}
