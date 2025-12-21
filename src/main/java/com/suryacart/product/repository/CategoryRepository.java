package com.suryacart.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suryacart.product.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	public Category findBycategoryId(Long id);

	public void deleteBycategoryId(Long id);
}
