package com.edu.shop.service;

import com.edu.shop.dto.ProductDTO;
import com.edu.shop.entity.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductInterface {
    ProductDTO save(ProductDTO productDTO);
    ProductDTO update(ProductDTO productDTO);
    List<ProductDTO> getAllProducts();
    List<ProductDTO> getAllByCategoryId(UUID categoryId);

    ProductDTO getById(UUID productId);

    Boolean delete(UUID ProductId);


}
