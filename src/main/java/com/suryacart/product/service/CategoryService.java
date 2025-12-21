package com.suryacart.product.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.suryacart.product.model.Category;

@Component
public interface CategoryService {

	List<Category> findAll();

	Category findById(Long id);

	Category save(Category category);

	void deleteById(Long id);
}
