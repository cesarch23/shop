package com.edu.shop.dto;

import java.util.List;
import java.util.UUID;

public class ShoppingDTO {
    private UUID shoppingId;
    private UUID clientId;
    private String paymentType;
    private String comment;
    private Boolean isActive;
    private List<PurchaseItemDTO> purchases;

    public  ShoppingDTO(){}
    public ShoppingDTO(UUID shoppingId, UUID clientId, String paymentType, String comment, Boolean isActive, List<PurchaseItemDTO> purchases) {
        this.shoppingId = shoppingId;
        this.clientId = clientId;
        this.paymentType = paymentType;
        this.comment = comment;
        this.isActive = isActive;
        this.purchases = purchases;
    }

    public UUID getShoppingId() {
        return shoppingId;
    }

    public void setShoppingId(UUID shoppingId) {
        this.shoppingId = shoppingId;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public List<PurchaseItemDTO> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<PurchaseItemDTO> purchases) {
        this.purchases = purchases;
    }
}
