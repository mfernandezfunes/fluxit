package com.fluxit.API.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fluxit.API.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
   
}