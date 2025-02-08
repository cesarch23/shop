package com.edu.shop.serviceImpls;


import com.edu.shop.dto.CategoryDTO;
import com.edu.shop.exception.BusinessException;
import com.edu.shop.exception.enums.BusinessExceptionReason;
import com.edu.shop.mapper.CategoryMapper;
import com.edu.shop.repository.CategoryRepository;
import com.edu.shop.service.CategoryInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService implements CategoryInterface {
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    @Transactional
    public CategoryDTO add(CategoryDTO categoryDTO) {
        if( existsByDescription (categoryDTO.getDescription()))
            throw new BusinessException(BusinessExceptionReason.ENTITY_EXITS,"La Categoria con la descripcion: "+categoryDTO.getDescription());
        return categoryMapper.toDTO(categoryRepository.save( categoryMapper.toEntity(categoryDTO)));
    }

    @Override
    public Optional<CategoryDTO> update(CategoryDTO categoryDTO) {
        int updatedRows = categoryRepository.update( categoryDTO.getDescription(),categoryDTO.getIsActive(), categoryDTO.getCategoryId() );
        if(updatedRows>0)
            return Optional.of( categoryMapper.toDTO(categoryRepository.findCategoryByDescription(categoryDTO.getDescription())));
        throw new BusinessException(BusinessExceptionReason.ENTITY_NOT_FOUND,"La categoria con la descripcion: "+categoryDTO.getDescription()) ;
    }

    @Override
    public Boolean delete(CategoryDTO categoryDTO) {
        int updatedRows = categoryRepository.delete(categoryDTO.getIsActive(), categoryDTO.getCategoryId());
        return updatedRows > 0;
    }

    @Override
    public List<CategoryDTO> getAll() {
        return categoryMapper.toListDTO(categoryRepository.findAll());
    }
    public Boolean existsByDescription(String description){
        return categoryRepository.existsByDescription(description);
    }
    public boolean existsById(UUID id){
        return categoryRepository.existsById(id);
    }

}
