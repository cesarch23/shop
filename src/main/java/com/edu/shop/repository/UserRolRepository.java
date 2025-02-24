package com.edu.shop.repository;

import com.edu.shop.entity.UserRol;
import com.edu.shop.entity.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRolRepository extends JpaRepository<UserRol, UserRoleId> {
}
