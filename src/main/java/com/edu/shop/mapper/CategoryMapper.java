package com.edu.shop.mapper;

import com.edu.shop.dto.CategoryDTO;
import com.edu.shop.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mappings({
            @Mapping(source = "id",target = "categoryId"),
            @Mapping(source = "description",target = "description"),
            @Mapping(source = "state",target = "isActive")
    })
    CategoryDTO toDTO(Category category);

    @Mappings({
            @Mapping(source = "categoryId",target = "id"),
            @Mapping(source = "description",target = "description"),
            @Mapping(source = "isActive",target = "state"),
            @Mapping(target = "product",ignore = true)
    })
    Category toEntity(CategoryDTO categoryDTO);
    List<CategoryDTO> toListDTO(List<Category> categories);

    /*this.id = id;
        this.description = description;
        this.state = state;
        this.product = ;*/
}
