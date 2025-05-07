package com.example.ticketing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ticketing.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {}
