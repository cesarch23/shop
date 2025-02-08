package com.edu.shop.repository;

import com.edu.shop.dto.ShoppingDTO;
import com.edu.shop.entity.Shopping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ShoppingRepository extends JpaRepository<Shopping, UUID> {
    List<Shopping> findByClientId( UUID clientId);
}
