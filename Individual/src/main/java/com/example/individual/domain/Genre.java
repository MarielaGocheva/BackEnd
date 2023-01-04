package com.example.individual.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;

@Builder
@Getter
@AllArgsConstructor
public class Genre {
    private Long id;
    private String name;
}
