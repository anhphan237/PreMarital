package com.example.premarital.user.repository;

import com.example.premarital.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findAllByUserId(String username, Pageable pageable);
}
