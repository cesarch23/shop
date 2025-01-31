package com.edu.shop.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "products")
public class Product {

    @Column(name = "product_id")
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="product_name", nullable = false,length = 45)
    private String name;

    @Column(name="product_barcode",nullable = false,unique = true,length = 150)
    private String barcode;

    @Column(name = "product_sale_price",nullable = false,precision = 2)
    private Double salePrice;

    @Column(name = "product_stock",nullable = false)
    private Integer stock;

    @Column(name = "product_state",nullable = false)
    private Boolean state;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "category_id", referencedColumnName="category_id",
            foreignKey = @ForeignKey(name = "fk_product_category"),
            updatable = false,insertable = true,unique = true,nullable = false)
    private Category category;
    //    @OneToMany(mappedBy = "product") no es necesario la relacion
//    private List<ShoppingProduct> shoppingProduct;
    public Product(){}
    public Product(UUID id, String name, String barcode, Double salePrice, Integer stock, Boolean state, Category category) {
        this.id = id;
        this.name = name;
        this.barcode = barcode;
        this.salePrice = salePrice;
        this.stock = stock;
        this.state = state;
        this.category = category;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}