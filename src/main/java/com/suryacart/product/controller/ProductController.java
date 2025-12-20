package com.suryacart.product.controller;

import com.suryacart.product.dto.ProductInternalDto;
import com.suryacart.product.dto.ProductRequestDto;
import com.suryacart.product.dto.ProductResponseDto;
import com.suryacart.product.service.ProductService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    // 1️⃣ Create Product
    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(
            @Valid @RequestBody ProductRequestDto request) {

        ProductResponseDto response = productService.createProduct(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 2️⃣ Get Product by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(
            @PathVariable UUID id) {

        return ResponseEntity.ok(productService.getProductById(id));
    }

    // 3️⃣ Get All Active Products
    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllActiveProducts());
    }

    // 4️⃣ Update Product
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(
            @PathVariable UUID id,
            @Valid @RequestBody ProductRequestDto request) {

        return ResponseEntity.ok(productService.updateProduct(id, request));
    }

    // 5️⃣ Delete Product (Soft delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    // 🔒 INTERNAL API (for Order Service)
    @GetMapping("/{id}/internal")
    public ResponseEntity<ProductInternalDto> getProductForOrder(
            @PathVariable UUID id) {

        return ResponseEntity.ok(productService.getProductForOrder(id));
    }
}
