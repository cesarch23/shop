package com.edu.shop.controller;

import com.edu.shop.dto.ShoppingDTO;
import com.edu.shop.exception.BusinessException;
import com.edu.shop.exception.enums.BusinessExceptionReason;
import com.edu.shop.serviceImpls.ClientService;
import com.edu.shop.serviceImpls.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/shopping")
public class PurchaseController {

    @Autowired
    ShoppingService shoppingService;
    @Autowired
    ClientService clientService;

    @GetMapping(path = "all")
    public ResponseEntity<List<ShoppingDTO>> getAll(){
        return new ResponseEntity<>(shoppingService.getAll(), HttpStatus.OK);
    }
    @PostMapping(path = "add")
    public ResponseEntity<ShoppingDTO> add(@RequestBody ShoppingDTO shoppingDTO){
        if(!clientService.exitById(shoppingDTO.getClientId())){
            throw new BusinessException(BusinessExceptionReason.ENTITY_NOT_FOUND,"El cliente");
        }
        return new ResponseEntity<>(shoppingService.save(shoppingDTO), HttpStatus.OK);
    }
}
