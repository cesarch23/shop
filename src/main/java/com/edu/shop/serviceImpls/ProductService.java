package com.edu.shop.serviceImpls;

import com.edu.shop.dto.ProductDTO;
import com.edu.shop.entity.Product;
import com.edu.shop.exception.BusinessException;
import com.edu.shop.exception.enums.BusinessExceptionReason;
import com.edu.shop.mapper.ProductMapper;
import com.edu.shop.repository.ProductRepository;
import com.edu.shop.service.ProductInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService implements ProductInterface {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;


    @Override
    public ProductDTO save(ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        if (!productRepository.existsByBarcode(product.getBarcode())) {
            return productMapper.toDTO( productRepository.save(product));
        }
        throw new BusinessException(BusinessExceptionReason.ENTITY_EXITS,"El producct con el barcode: "+productDTO.getBarcode());
    }

    @Override
    public ProductDTO update(ProductDTO productDTO) {
        if(!productRepository.existsById(productDTO.getProductId())){
            throw new BusinessException(BusinessExceptionReason.ENTITY_NOT_FOUND,"El producto "+productDTO.getName());
        }
        Product product = productRepository.save( productMapper.toEntity(productDTO));
        return productMapper.toDTO(product);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productMapper.toListDTO( productRepository.findAll() ) ;
    }

    @Override
    public List<ProductDTO> getAllByCategoryId(UUID categoryId) {
        return productMapper.toListDTO(productRepository.findAllByCategoryId(categoryId));
    }

    @Override
    public ProductDTO getById(UUID productId) {
        Optional<Product> productFounded = productRepository.findById(productId);
        return productFounded.map(this.productMapper::toDTO)
                .orElseThrow(()->new BusinessException(BusinessExceptionReason.ENTITY_NOT_FOUND,"El producto"));

    }

    @Override
    public Boolean delete(UUID productId) {
        if(!productRepository.existsById(productId)){
            throw new BusinessException(BusinessExceptionReason.ENTITY_NOT_FOUND,"El producto ");
        }
        Optional<Product> productFounded = productRepository.findById( productId );
        productFounded.map(p-> {
            p.setState(false);
            return p;
        });
        return productFounded.map(p-> {
                    productRepository.save(p);
                    return true;
                }).orElse(false);

    }
    public boolean existById(UUID productId){
        return productRepository.existsById(productId);
    }
}
