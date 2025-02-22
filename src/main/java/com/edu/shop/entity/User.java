package com.edu.shop.entity;

import jakarta.persistence.*;


import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name="user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name="user_name", nullable = false)
    private String name;
    @Column(name="user_password", nullable = false)
    private String password;
    @Column(name="user_email", nullable = false,unique = true,length = 52)
    private String username;//este sirve para el security que en realidad va ser el email
    @Column(name="user_locked", nullable = false)
    private boolean locked;
    @Column(name="user_disabled", nullable = false,length = 52)//todo verificar como se guarda en la base de datos
    private boolean disabled;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<UserRol> roles;

    public User(){}

    public User(UUID id, String name, String password, String username, boolean locked, boolean disabled) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.username = username;
        this.locked = locked;
        this.disabled = disabled;
    }

    public List<UserRol> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRol> roles) {
        this.roles = roles;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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


    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
