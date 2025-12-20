package com.suryacart.product.service.impl;

import com.suryacart.product.dto.ProductInternalDto;
import com.suryacart.product.dto.ProductRequestDto;
import com.suryacart.product.dto.ProductResponseDto;
import com.suryacart.product.exception.ProductNotFoundException;
import com.suryacart.product.model.Product;
import com.suryacart.product.repository.ProductRepository;
import com.suryacart.product.service.ProductService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto request) {
        Product product = Product.builder().build();
        mapRequestToEntity(request, product);
        Product saved = productRepository.save(product);
        return mapEntityToResponse(saved);
    }

    @Override
    public ProductResponseDto getProductById(UUID productId) {
        Product product = getActiveProduct(productId);
        return mapEntityToResponse(product);
    }

    @Override
    public List<ProductResponseDto> getAllActiveProducts() {
        return productRepository.findByActiveTrue()
                .stream()
                .map(this::mapEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto updateProduct(UUID productId, ProductRequestDto request) {
        Product product = getActiveProduct(productId);
        mapRequestToEntity(request, product);
        return mapEntityToResponse(productRepository.save(product));
    }

    @Override
    public void deleteProduct(UUID productId) {
        Product product = getActiveProduct(productId);
        product.setActive(false);
        productRepository.save(product);
    }

    @Override
    public ProductInternalDto getProductForOrder(UUID productId) {
        Product product = getActiveProduct(productId);

        ProductInternalDto dto = new ProductInternalDto();
        dto.setId(product.getId());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());

        return dto;
    }

    // 🔒 Private helpers

    private Product getActiveProduct(UUID productId) {
        return productRepository.findById(productId)
                .filter(Product::getActive)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found with id: " + productId));
    }

    private void mapRequestToEntity(ProductRequestDto request, Product product) {
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setCategory(request.getCategory());
        product.setActive(true);
    }

    private ProductResponseDto mapEntityToResponse(Product product) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        dto.setCategory(product.getCategory());
        dto.setActive(product.getActive());
        dto.setCreatedAt(product.getCreatedAt());
        dto.setUpdatedAt(product.getUpdatedAt());
        return dto;
    }
}
