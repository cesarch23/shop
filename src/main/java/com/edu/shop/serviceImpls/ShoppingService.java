package com.edu.shop.serviceImpls;

import com.edu.shop.dto.PurchaseItemDTO;
import com.edu.shop.dto.ShoppingDTO;
import com.edu.shop.entity.*;
import com.edu.shop.exception.BusinessException;
import com.edu.shop.exception.enums.BusinessExceptionReason;
import com.edu.shop.mapper.ShoppingMapper;
import com.edu.shop.repository.ClientRepository;
import com.edu.shop.repository.ProductRepository;
import com.edu.shop.repository.ShoppingRepository;
import com.edu.shop.service.ShoppingInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ShoppingService implements ShoppingInterface {
    @Autowired
    ShoppingRepository shoppingRepository;
    @Autowired
    ShoppingMapper shoppingMapper;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ProductRepository productRepository;


    @Override
    @Transactional
    public ShoppingDTO save(ShoppingDTO shoppingDTO) {

        Client client = clientRepository.findById(shoppingDTO.getClientId())
                .orElseThrow(() -> new BusinessException(BusinessExceptionReason.ENTITY_NOT_FOUND,"El cliente"));

        Shopping shopping = new Shopping();
        shopping.setDate(new Date());
        shopping.setPaymentMethod(shoppingDTO.getPaymentType());
        shopping.setComment(shoppingDTO.getComment());
        shopping.setState(shoppingDTO.getIsActive());
        shopping.setClientId(shoppingDTO.getClientId());
        shopping.setClient(client);

        //Mapear los productos de la compra
        List<ShoppingProduct> shoppingProducts = shoppingDTO.getPurchases().stream()
                .map(purchaseItemDTO -> {
                        Product product = productRepository.findById(purchaseItemDTO.getProductId())
                                .orElseThrow(() ->new BusinessException(BusinessExceptionReason.ENTITY_NOT_FOUND, "El producto"));
                        int stockAvailable = product.getStock() - purchaseItemDTO.getQuantity();
                        if(stockAvailable<0){
                            throw new BusinessException(BusinessExceptionReason.PRODUCT_STOCK_UNAVAILABLE,product.getName());
                        }
                        product.setStock( stockAvailable );

                        ShoppingProductPK shoppingProductPK = new ShoppingProductPK(shopping.getId(), product.getId());


                        ShoppingProduct shoppingProduct = new ShoppingProduct();
                        shoppingProduct.setId(shoppingProductPK);
                        shoppingProduct.setShopping(shopping);
                        shoppingProduct.setProduct(product);
                        shoppingProduct.setQuantity(purchaseItemDTO.getQuantity());
                        shoppingProduct.setTotal(purchaseItemDTO.getQuantity()*product.getSalePrice());
                        shoppingProduct.setState(purchaseItemDTO.getIsActive());

                        return shoppingProduct; })
                .collect(Collectors.toList());


        shopping.setShoppingProduct(shoppingProducts);
        Shopping shopping1 = shoppingRepository.save(shopping);
        return shoppingMapper.toDTO(shopping1);
    }

    @Override
    public ShoppingDTO getByClientId(UUID clientId) {
        return null;
    }


    @Override
    public List<ShoppingDTO> getAll() {
        return shoppingMapper.toDTOList(shoppingRepository.findAll());
    }
}
