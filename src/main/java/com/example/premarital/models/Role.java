package com.example.premarital.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "is_active")
    private Boolean isActive;
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<User> users;
}
