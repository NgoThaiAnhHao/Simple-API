package com.springlove.api_demo.dto;

import jakarta.validation.constraints.Positive;

public class ProductPatchDto {
    private String name;

    @Positive(message = "Price must be greater than 0")
    private Double price;

    @Positive(message = "Stock must be greater than 0")
    private Integer stock;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
