package com.edu.shop.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;
@IdClass(UserRoleId.class)
@Entity
@Table(name = "roles")
public class UserRol {

    @Id
    @Column(name = "user_id",nullable = false)
    private UUID id;
    @Id
    @Column(nullable = false,length = 25)
    private String role;
    @Column(nullable = false,length = 25)
    private LocalDateTime grantedDate;
    @ManyToOne()
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "user_id",
            insertable = false,
            updatable = false
    )
    private User user;
    public UserRol(){}
    public UserRol(UUID id, String role, LocalDateTime grantedDate, User user) {
        this.id = id;
        this.role = role;
        this.grantedDate = grantedDate;
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getGrantedDate() {
        return grantedDate;
    }

    public void setGrantedDate(LocalDateTime grantedDate) {
        this.grantedDate = grantedDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
