package com.example.individual.repository.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fName")
    private String fName;

    @Column(name = "lName")
    private String lName;

    @Column(name = "email")
    private String email;

    @Column(name = "role")
    private String role;

    @Column(name = "salt")
    private String salt;

    @Column(name = "encryptedPass")
    private String encryptedPass;
}
