package com.edu.shop.repository;

import com.edu.shop.dto.ClientDTO;
import com.edu.shop.entity.Client;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {

    Optional<Client> findClientByEmail(String email);
    boolean existsClientByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE Client c SET " +
            "c.name = COALESCE(:name, c.name), " +
            "c.surname = COALESCE(:lastname, c.surname), " +
            "c.phoneNumber = COALESCE(:number, c.phoneNumber), " +
            "c.address = COALESCE(:address, c.address) " +
            "WHERE c.email = :mail")
    int updateClient(
            @Param("name") String name,
            @Param("lastname") String lastname,
            @Param("number") String number,
            @Param("address") String address,
            @Param("mail") String mail
    );
}
