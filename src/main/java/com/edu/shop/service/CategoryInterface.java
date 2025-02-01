package com.edu.shop.service;

import com.edu.shop.dto.CategoryDTO;

import java.util.List;
import java.util.Optional;

public interface CategoryInterface {
    CategoryDTO add(CategoryDTO categoryDTO);
    Optional<CategoryDTO> update(CategoryDTO categoryDTO);
    Boolean delete(CategoryDTO categoryDTO);
    List<CategoryDTO> getAll();
}
