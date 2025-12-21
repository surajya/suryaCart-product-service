package com.suryacart.product.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suryacart.product.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

	public List<Product> findByActiveTrue();

	// Custom query method to find products by category ID
	List<Product> findByCategory_CategoryId(Integer categoryId);

}
