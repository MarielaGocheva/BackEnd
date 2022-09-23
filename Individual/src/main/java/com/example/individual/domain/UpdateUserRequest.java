package com.example.individual.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
    private Long id;
    private String fName;
    private String lName;
    private String email;
    private String role;
    private String salt;
    private String encryptedPass;
}
