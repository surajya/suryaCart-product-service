package com.suryacart.product.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.suryacart.product.dto.ProductInternalDto;
import com.suryacart.product.dto.ProductRequestDto;
import com.suryacart.product.dto.ProductResponseDto;

@Component
public interface ProductService {

	ProductResponseDto createProduct(ProductRequestDto request);

	ProductResponseDto getProductById(UUID productId);

	List<ProductResponseDto> getAllActiveProducts();

	ProductResponseDto updateProduct(UUID productId, ProductRequestDto request);

	void deleteProduct(UUID productId);

	ProductInternalDto getProductForOrder(UUID productId);
}
