package com.example.individual.domain;

import com.example.individual.domain.enums.Role;
import lombok.*;


@Builder
@Getter
@AllArgsConstructor
public class User {
    private Long id;
    private String fName;
    private String lName;
    private String email;
    private Role role;
    private String password;
}
