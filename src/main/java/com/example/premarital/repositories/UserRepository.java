package com.example.premarital.repositories;

import com.example.premarital.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}