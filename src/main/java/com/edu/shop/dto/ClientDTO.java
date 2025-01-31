package com.edu.shop.dto;

import java.util.UUID;

public class ClientDTO {
    private UUID clientId;
    private String name;
    private String lastname;
    private String  telephoneNumber;
    private String address;
    private String mail;
    public ClientDTO(){}

    public ClientDTO(UUID clientId, String name, String lastname, String telephoneNumber, String address, String mail) {
        this.clientId = clientId;
        this.name = name;
        this.lastname = lastname;
        this.telephoneNumber = telephoneNumber;
        this.address = address;
        this.mail = mail;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
