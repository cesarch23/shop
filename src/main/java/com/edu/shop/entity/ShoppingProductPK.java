package com.edu.shop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class ShoppingProductPK implements Serializable {

    @Column(name = "shopping_id")
    private UUID shoppingId;

    @Column(name = "product_id")
    private UUID productId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingProductPK that = (ShoppingProductPK) o;
        return Objects.equals(shoppingId, that.shoppingId) && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shoppingId, productId);
    }
}