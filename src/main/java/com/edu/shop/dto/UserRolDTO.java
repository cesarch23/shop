package com.edu.shop.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserRolDTO {

    private String role;

    public UserRolDTO(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
