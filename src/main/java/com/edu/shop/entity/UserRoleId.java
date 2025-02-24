package com.edu.shop.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class UserRoleId implements Serializable {
    private UUID userId;
    private String role;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID id) {
        this.userId = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRoleId that)) return false;
        return Objects.equals(userId, that.userId) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, role);
    }
}
