package com.edu.shop.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class ProductDTO {


    private UUID productId;

    private String name;

    private String barcode;

    private Double price;

    private Integer stock;

    private Boolean isActive;

    private UUID categoryId;
    private CategoryDTO categoryDTO;



    public ProductDTO(){}

    public ProductDTO(UUID productId, String name, String barcode, Double price, Integer stock, Boolean isActive, UUID categoryId, CategoryDTO categoryDTO) {
        this.productId = productId;
        this.name = name;
        this.barcode = barcode;
        this.price = price;
        this.stock = stock;
        this.isActive = isActive;
        this.categoryId = categoryId;
        this.categoryDTO = categoryDTO;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }

    public CategoryDTO getCategoryDTO() {
        return categoryDTO;
    }

    public void setCategoryDTO(CategoryDTO categoryDTO) {
        this.categoryDTO = categoryDTO;
    }

}
