package com.edu.shop.mapper;

import com.edu.shop.dto.PurchaseItemDTO;
import com.edu.shop.entity.Product;
import com.edu.shop.entity.ShoppingProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring",uses = {ProductMapper.class})//TODO ELIMINAR ESTO COMO TEST
public interface ShoppingProductMapper {
    @Mappings({
            @Mapping(source = "productId" ,target = "id.productId"),
            @Mapping(target = "id.shoppingId",ignore = true),
            @Mapping(source = "quantity" ,target = "quantity"),
            @Mapping(source = "total" ,target = "total"),
            @Mapping(source = "isActive" ,target = "state"),
            @Mapping(target = "shopping" ,ignore = true),
            @Mapping(target = "product" ,ignore = true)
    })
    ShoppingProduct toEntity(PurchaseItemDTO purchaseItemDTO);
    List<ShoppingProduct> toEntityList(List<PurchaseItemDTO> purchaseItemDTOS);

    @Mappings({
            @Mapping(source = "id.productId" ,target = "productId"),
            @Mapping(source = "quantity" ,target = "quantity"),
            @Mapping(source = "total" ,target = "total"),
            @Mapping(source = "state" ,target = "isActive")
    })
    PurchaseItemDTO toDTO(ShoppingProduct shoppingProduct);
    List<PurchaseItemDTO> toDTOList(List<ShoppingProduct> shoppingProductList);


}
