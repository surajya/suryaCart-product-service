package com.suryacart.product.dto;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Data;

@Data
public class ProductInternalDto {

    private UUID id;
    private BigDecimal price;
    private Integer stock;

}
