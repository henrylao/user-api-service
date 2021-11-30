package com.hcl.spring.jpa.sql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.spring.jpa.sql.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
