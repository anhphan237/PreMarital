package com.example.premarital.repositories;

import com.example.premarital.models.Role;
import com.example.premarital.models.WithdrawRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Page<Role> findRolesByIsActiveTrue(Pageable pageable);
}
