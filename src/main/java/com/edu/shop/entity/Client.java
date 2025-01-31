package com.edu.shop.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "clients")
public class Client {

    @Column(name = "client_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private UUID id;

    @Column(name = "client_name", nullable = false,length = 40)
    private String name;

    @Column(name = "client_surname",nullable=false,length = 100)
    private String surname;
    @Column(name = "client_phone_number",length = 9)
    private String phoneNumber;
    @Column(name = "client_address",length = 80)
    private String address;
    @Column(name = "client_email", nullable = false,unique = true)
    private String email;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "client")
    //TODO testear para recuperar solo client y luego la lista de sus compras
    private List<Shopping> shopping;

    public Client(){}
    public Client(UUID id, String name, String surname, String phoneNumber, String address, String email, List<Shopping> shopping) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.shopping = shopping;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Shopping> getShopping() {
        return shopping;
    }

    public void setShopping(List<Shopping> shopping) {
        this.shopping = shopping;
    }
}