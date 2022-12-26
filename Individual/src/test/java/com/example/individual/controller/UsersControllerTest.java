package com.example.individual.controller;

import com.example.individual.business.*;
import com.example.individual.domain.*;
import com.example.individual.domain.enums.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UsersController.class)
class UsersControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CreateUserUseCase createUserUseCase;
    @MockBean
    private UpdateUserUseCase updateUserUseCase;
    @MockBean
    private GetUserUseCase getUserUseCase;
    @MockBean
    private GetUsersUseCase getUsersUseCase;
    @MockBean
    private GetArtistPageInfoUseCase getArtistPageInfoUseCase;
    @MockBean
    private AccessTokenDecoder accessTokenDecoder;

    @Test
    void createUser() throws Exception {
        CreateUserRequest request = CreateUserRequest.builder().email("mariela@gmail").fName("Mariela").lName("Gocheva").password("some-password").role("DJ").build();
        CreateUserResponse response = CreateUserResponse.builder().userId(2L).build();
        when(createUserUseCase.createUser(request)).thenReturn(response);
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                    {"fname": "Mariela", "lname": "Gocheva", "email": "mariela@gmail", "role": "DJ", "password": "some-password"}
                """)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                {"userId": 2}
            """))
        ;
        verify(createUserUseCase).createUser(request);
    }

    @Test
    void getAllUsers() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(User.builder().id(1L).email("mariela@gmail").fName("Mariela").lName("Gocheva").password("some-password").role(Role.DJ).build());
        GetAllUsersResponse response = GetAllUsersResponse.builder().users(users).build();
        GetAllUsersRequest request = GetAllUsersRequest.builder().build();
        when(getUsersUseCase.getUsers(request)).thenReturn(response);
        mockMvc.perform(get("/users")).andDo(print()).andExpect(status().isOk()).andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE)).andExpect(content().json("""
            {"users":[{"id":1,"email":"mariela@gmail","role":"DJ","password":"some-password","fname":"Mariela","lname":"Gocheva"}]}
        """));
        verify(getUsersUseCase).getUsers(request);
    }
}