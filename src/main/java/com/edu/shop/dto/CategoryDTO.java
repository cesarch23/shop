package com.edu.shop.dto;

import java.util.UUID;

public class CategoryDTO {
    private UUID categoryId;
    private String descrioption;
    private Boolean isActive;
    public CategoryDTO(){

    }

    public CategoryDTO(UUID categoryId, String descrioption, Boolean isActive) {
        this.categoryId = categoryId;
        this.descrioption = descrioption;
        this.isActive = isActive;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescrioption() {
        return descrioption;
    }

    public void setDescrioption(String descrioption) {
        this.descrioption = descrioption;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
