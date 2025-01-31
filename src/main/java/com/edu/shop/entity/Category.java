package com.edu.shop.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "categories")
public class Category {

    @Column(name = "category_id")
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "category_description",nullable = false,length = 45)
    private String description;
    @Column(name = "category_state",nullable = false)
    private Boolean state;

    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // TODO con cascade se puede crear crear una categoria con todos los productos que contenga
    private List<Product> product;

    public Category(){}
    public Category(UUID id, String description, Boolean state, List<Product> product) {
        this.id = id;
        this.description = description;
        this.state = state;
        this.product = product;
    }
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }
}