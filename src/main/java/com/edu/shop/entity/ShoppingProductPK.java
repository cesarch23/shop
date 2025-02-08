package com.edu.shop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class ShoppingProductPK implements Serializable{

    @Column(name = "shopping_id")
    private UUID shoppingId;

    @Column(name = "product_id")
    private UUID productId;
    public ShoppingProductPK(){}
    public ShoppingProductPK(UUID shoppingId, UUID productId) {
        this.shoppingId = shoppingId;
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShoppingProductPK that)) return false;
        return Objects.equals(getShoppingId(), that.getShoppingId()) && Objects.equals(getProductId(), that.getProductId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getShoppingId(), getProductId());
    }

    public UUID getShoppingId() {
        return shoppingId;
    }

    public void setShoppingId(UUID shoppingId) {
        this.shoppingId = shoppingId;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }
}