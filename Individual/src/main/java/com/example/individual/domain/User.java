package com.example.individual.domain;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String fName;
    private String lName;
    private String email;
    private String role;
    private String password;
}
