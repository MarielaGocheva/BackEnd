package com.example.individual.domain;

import com.example.individual.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


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
