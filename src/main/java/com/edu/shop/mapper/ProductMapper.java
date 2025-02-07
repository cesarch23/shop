package com.edu.shop.mapper;


import com.edu.shop.dto.ProductDTO;
import com.edu.shop.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {
    @Mappings({
            @Mapping(source = "id",target = "productId"),
            @Mapping(source = "name",target = "name"),
            @Mapping(source = "barcode",target = "barcode"),
            @Mapping(source = "salePrice",target = "price"),
            @Mapping(source = "stock",target = "stock"),
            @Mapping(source = "state",target = "isActive"),
            @Mapping(source = "categoryId",target = "categoryId"),
            @Mapping(source = "category",target = "categoryDTO")
    })
    ProductDTO toDTO(Product product);
    @Mappings({
            @Mapping(source = "productId",target = "id"),
            @Mapping(source = "name",target = "name"),
            @Mapping(source = "barcode",target = "barcode"),
            @Mapping(source = "price",target = "salePrice"),
            @Mapping(source = "stock",target = "stock"),
            @Mapping(source = "isActive",target = "state"),
            @Mapping(source = "categoryId",target = "categoryId"),
            @Mapping(source = "categoryDTO",target = "category"),

    })
    Product toEntity(ProductDTO productDTO);
    List<ProductDTO> toListDTO(List<Product> productList);
/* Entity
  this.id = id;
        this.name = name;
        this.barcode = barcode;
        this.salePrice = salePrice;
        this.stock = stock;
        this.state = state;
        this.category = category;

private UUID productId;
    private String name;
    private String barcode;
    private Double price;
    private Integer stock;
    private Boolean isActive;
    private UUID categoryId;
    private CategoryDTO categoryDTO;

*
*
* */
}
