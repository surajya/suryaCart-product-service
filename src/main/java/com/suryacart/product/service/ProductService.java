package com.suryacart.product.service;

import com.suryacart.product.dto.ProductInternalDto;
import com.suryacart.product.dto.ProductRequestDto;
import com.suryacart.product.dto.ProductResponseDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    ProductResponseDto createProduct(ProductRequestDto request);

    ProductResponseDto getProductById(UUID productId);

    List<ProductResponseDto> getAllActiveProducts();

    ProductResponseDto updateProduct(UUID productId, ProductRequestDto request);

    void deleteProduct(UUID productId);

    ProductInternalDto getProductForOrder(UUID productId);
}
