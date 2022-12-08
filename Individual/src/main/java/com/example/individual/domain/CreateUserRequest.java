package com.example.individual.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    private long id;
    private String fName;
    private String lName;
    private String email;
    private String role;
    private String password;
}
