package com.edu.shop.mapper;

import com.edu.shop.dto.ShoppingDTO;
import com.edu.shop.entity.Shopping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring",uses = { ShoppingProductMapper.class })
public interface ShoppingMapper {
    /*
    @Mappings({
            @Mapping(source = "shoppingId",target = "id"),
            @Mapping(source = "clientId",target = "clientId"),
            //@Mapping(target = "date",ignore = true),
            @Mapping(source = "paymentType",target = "paymentMethod"),
            @Mapping(source = "comment",target = "comment"),
            @Mapping(source = "isActive",target = "state"),
            @Mapping(source = "purchases",target = "shoppingProduct"),
            //@Mapping(target = "client", ignore = true),
    })
    Shopping toEntity(ShoppingDTO shoppingDTO);
    List<Shopping> toEntityList(List<ShoppingDTO> shoppingDTOS);*/
    @Mappings({
            @Mapping(source = "id",target = "shoppingId"),
            @Mapping(source = "clientId",target = "clientId"),
            @Mapping(source = "paymentMethod",target = "paymentType"),
            @Mapping(source = "comment",target = "comment"),
            @Mapping(source = "state",target = "isActive"),
            @Mapping(source = "shoppingProduct",target = "purchases"),
    })
    ShoppingDTO toDTO(Shopping shopping);
    List<ShoppingDTO> toDTOList(List<Shopping> shoppings);

}
