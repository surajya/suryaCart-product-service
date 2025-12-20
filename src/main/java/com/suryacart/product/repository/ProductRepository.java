package com.suryacart.product.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suryacart.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, UUID> {

	public List<Product> findByActiveTrue();

	// Custom query method to find products by category ID
	public List<Product> findByCategoryId(int categoryId);

}
