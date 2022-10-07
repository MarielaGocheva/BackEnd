package com.example.individual.repository.entity;

import lombok.*;
import org.springframework.context.annotation.Bean;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
//@Table(name = "country")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    //@Generated
    private Long id;

    private String fName;
    private String lName;
    private String email;
    private String role;
    private String salt;
    private String encryptedPass;
}
