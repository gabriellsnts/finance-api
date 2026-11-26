package com.portfolio.finance_api.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name ="email",nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false)
    private String password;


    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;


    @PrePersist
    protected void onCreated(){
        this.createdAt = LocalDateTime.now();
    }


}
