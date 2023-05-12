package com.example.individual.business.impl;

import com.example.individual.repository.PlaylistRepository;
import com.example.individual.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class SearchUseCaseImplTest {
    @Mock
    private PlaylistRepository playlistRepositoryMock;
    @Mock
    private UserRepository userRepositoryMock;
    @InjectMocks
    private SearchUseCaseImpl searchUseCase;

    @Test
    void search_valid(){

    }

}