package com.example.CrudWeb.auth.repository;

import com.example.CrudWeb.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email) ;
    User findByUsername(String username) ;
}
