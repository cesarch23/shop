package com.edu.shop.controller;

import com.edu.shop.dto.ProductDTO;
import com.edu.shop.exception.BusinessException;
import com.edu.shop.exception.enums.BusinessExceptionReason;
import com.edu.shop.serviceImpls.CategoryService;
import com.edu.shop.serviceImpls.ProductService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;


    @GetMapping(path = "all")
    public ResponseEntity<List<ProductDTO>> getAll(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }
    @PostMapping(path = "add")
    public  ResponseEntity<ProductDTO> add( @RequestBody ProductDTO productDTO){
        if(!categoryService.existsById(productDTO.getCategoryId())){
            throw new BusinessException(BusinessExceptionReason.ENTITY_NOT_FOUND,"La categoria con el id: "+productDTO.getCategoryId());
        }
        return new ResponseEntity<>(productService.save(productDTO),HttpStatus.CREATED);
    }
    @PatchMapping(path = "update")
    public  ResponseEntity<ProductDTO> update(@RequestBody ProductDTO productDTO){
        if(!categoryService.existsById(productDTO.getCategoryId())){
            throw new BusinessException(BusinessExceptionReason.ENTITY_NOT_FOUND,"La categoria con el id: "+productDTO.getCategoryId());
        }
        return new ResponseEntity<>(productService.update(productDTO),HttpStatus.OK);
    }
    @GetMapping(path = "find/{productId}")
    public ResponseEntity<ProductDTO> findById(@PathVariable UUID productId){
        return new ResponseEntity<>(productService.getById(productId),HttpStatus.OK);
    }
    @DeleteMapping(path = "delete/{productId}")
    public ResponseEntity<Boolean> delete(@PathVariable UUID productId){
        return new ResponseEntity<>(productService.delete(productId),HttpStatus.OK);
    }
    @GetMapping(path = "category/{categoryId}")
    public ResponseEntity<List<ProductDTO>> getAllByCategoryId(@PathVariable UUID categoryId){
        if(!categoryService.existsById(categoryId)){
            throw new BusinessException(BusinessExceptionReason.ENTITY_NOT_FOUND,"La categoria con el id: "+categoryId);
        }
        return new ResponseEntity<>(productService.getAllByCategoryId(categoryId),HttpStatus.OK);
    }


}
