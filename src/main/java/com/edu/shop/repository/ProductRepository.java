package com.edu.shop.repository;

import com.edu.shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    boolean existsByBarcode(String barcode);
    List<Product> findAllByCategoryId(UUID categoryId);
}
