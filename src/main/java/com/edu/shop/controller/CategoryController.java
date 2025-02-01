package com.edu.shop.controller;

import com.edu.shop.dto.CategoryDTO;
import com.edu.shop.exception.BusinessException;
import com.edu.shop.exception.enums.BusinessExceptionReason;
import com.edu.shop.serviceImpls.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping(path = "api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping(path = "all")
    public ResponseEntity<List<CategoryDTO>> getAll(){
        return new ResponseEntity<>(categoryService.getAll(), HttpStatus.OK);
    }
    @PostMapping(path = "add")
    public ResponseEntity<CategoryDTO> add(@RequestBody CategoryDTO categoryDTO ){
        return new ResponseEntity<>( categoryService.add(categoryDTO) ,HttpStatus.CREATED);
    }
    @PatchMapping(path = "update")
    public ResponseEntity<CategoryDTO> update(@RequestBody CategoryDTO categoryDTO){
        return new ResponseEntity<>( categoryService.update(categoryDTO).get(), HttpStatus.OK);

    }
    @DeleteMapping(path = "delete")
    public ResponseEntity<Boolean> delete(@RequestBody CategoryDTO categoryDTO){
        return new ResponseEntity<>(categoryService.delete(categoryDTO),HttpStatus.OK);
    }


}
