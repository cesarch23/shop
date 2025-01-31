package com.edu.shop.dto;

import java.util.UUID;

public class PurchaseItemDTO {
    private UUID productId;
    private Integer quantity;
    private Double total;
    private Boolean isActive;
    public PurchaseItemDTO(){}

    public PurchaseItemDTO(UUID productId, Integer quantity, Double total, Boolean isActive) {
        this.productId = productId;
        this.quantity = quantity;
        this.total = total;
        this.isActive = isActive;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
