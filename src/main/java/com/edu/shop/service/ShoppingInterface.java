package com.edu.shop.service;

import com.edu.shop.dto.ShoppingDTO;

import java.util.List;
import java.util.UUID;

public interface ShoppingInterface {
    ShoppingDTO save(ShoppingDTO shoppingDTO);
    ShoppingDTO getByClientId(UUID clientId);
    List<ShoppingDTO> getAll();
}
