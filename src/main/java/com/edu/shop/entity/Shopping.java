package com.edu.shop.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "shopping")
public class Shopping {
    @Column(name = "shopping_id")
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "shopping_date",nullable = false)
    private Date date;
    @Column(name = "shopping_payment_method",nullable = false)
    private String paymentMethod;
    @Column(name = "shopping_comment",length = 300)
    private String comment;
    @Column(name = "shopping_state",nullable = false)
    private Boolean state;
    @Column(name = "client_id",nullable = false)
    private UUID clientId;
    @ManyToOne()
    @JoinColumn(
            name = "client_id",
            foreignKey = @ForeignKey(name = "fk_shopping_client"),
            referencedColumnName = "client_id",
            nullable = false,insertable = false,
            updatable = false)
    private Client client;

    @OneToMany(mappedBy = "shopping", cascade = {CascadeType.ALL})
    private List<ShoppingProduct> shoppingProduct;

    public Shopping(){}

    public Shopping(UUID id, Date date, String paymentMethod, String comment, Boolean state, UUID clientId, Client client, List<ShoppingProduct> shoppingProduct) {
        this.id = id;
        this.date = date;
        this.paymentMethod = paymentMethod;
        this.comment = comment;
        this.state = state;
        this.clientId = clientId;
        this.client = client;
        this.shoppingProduct = shoppingProduct;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<ShoppingProduct> getShoppingProduct() {
        return shoppingProduct;
    }

    public void setShoppingProduct(List<ShoppingProduct> shoppingProduct) {
        this.shoppingProduct = shoppingProduct;
    }
}
