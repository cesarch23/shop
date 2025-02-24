package com.edu.shop.dto;


import java.util.List;
import java.util.UUID;

public class UserDTO {
    private UUID id;
    private String email;
    private String name;
    private String password;
    private List<UserRolDTO> roles;
    public UserDTO(){}

    public UserDTO(UUID id, String email, String name, List<UserRolDTO> roles) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.roles = roles;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserRolDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRolDTO> roles) {
        this.roles = roles;
    }
}
