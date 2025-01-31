package com.edu.shop.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "shopping_product")
public class ShoppingProduct {

    @EmbeddedId()
    private ShoppingProductPK id;
    @Column(name = "shopping_produt_quantity",nullable = false)
    private Integer quantity;

    @Column(name = "shopping_produt_total",nullable = false)
    private Double total;

    @Column(name = "shopping_produt_state",nullable = false)
    private Boolean state;

    @ManyToOne
    @MapsId("shoppingId")//vincula el id de esta clase con el de shopping
    @JoinColumn(
            name = "shopping_id",
            referencedColumnName = "shopping_id",
            foreignKey = @ForeignKey(name = "fk_shopping_product_shopping"),
            insertable = false,
            updatable = false,
            nullable = false
    )
    private Shopping shopping;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(
            name = "product_id",
            referencedColumnName = "product_id",
            foreignKey = @ForeignKey(name = "fk_shopping_product_product"),
            updatable = false,
            insertable = false,
            nullable = false
    )
    private Product product;
    public  ShoppingProduct(){}

    public ShoppingProduct(ShoppingProductPK id, Integer quantity, Double total, Boolean state, Shopping shopping, Product product) {
        this.id = id;
        this.quantity = quantity;
        this.total = total;
        this.state = state;
        this.shopping = shopping;
        this.product = product;
    }

    public ShoppingProductPK getId() {
        return id;
    }

    public void setId(ShoppingProductPK id) {
        this.id = id;
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

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Shopping getShopping() {
        return shopping;
    }

    public void setShopping(Shopping shopping) {
        this.shopping = shopping;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
