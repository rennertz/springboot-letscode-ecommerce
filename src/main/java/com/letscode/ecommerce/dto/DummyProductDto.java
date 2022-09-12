package com.letscode.ecommerce.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DummyProductDto {
    
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private Double discountPercentage;
    private Double rating;
    private Integer stock;
    private String brand;
    private String category;

}
