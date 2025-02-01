package com.edu.shop.repository;

import com.edu.shop.entity.Category;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {


    @Modifying
    @Transactional
    @Query("update Category c set c.description=:description, c.state=:state where c.id=:id ")
    Integer update(@Param("description") String description,@Param("state") Boolean state, @Param("id") UUID id);

    Boolean existsByDescription(String description);
    Category findCategoryByDescription(String description);

    @Modifying
    @Transactional
    @Query("update Category c set c.state=:state where c.id=:id")
    Integer delete(@Param("state") Boolean state,  @Param("id") UUID id);
}
