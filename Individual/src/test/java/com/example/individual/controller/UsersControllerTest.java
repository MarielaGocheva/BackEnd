package com.example.individual.controller;

import com.example.individual.business.*;
import com.example.individual.domain.*;
import com.example.individual.domain.enums.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Test
    void getUser() throws Exception {
        User user = User.builder().id(2L).role(Role.DJ).lName("Gocheva").fName("Mariela").email("mariela@gmail").password("pass").build();
        GetUserRequest request = GetUserRequest.builder().id(2L).build();
        GetUserResponse response = GetUserResponse.builder().user(user).build();
        when(getUserUseCase.getUser(request)).thenReturn(response);
        mockMvc.perform(get("/users/2")).andDo(print()).andExpect(status().isOk()).andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE)).andExpect(content().json("""
            {"user":{"id":2,"email":"mariela@gmail","role":"DJ","password":"pass","fname":"Mariela","lname":"Gocheva"}}
        """));
        verify(getUserUseCase).getUser(request);
    }

    @Test
    void updateUser() throws Exception {
        UpdateUserRequest request = UpdateUserRequest.builder().id(2L).role("DJ").lName("Gocheva").fName("Mariela").email("mariela@gmail").password("new-pass").build();
        mockMvc.perform(MockMvcRequestBuilders.put("/users/2")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                    {"id": 2, "fname": "Mariela", "lname": "Gocheva", "email": "mariela@gmail", "role": "DJ", "password": "new-pass"}
                """)
                )
                .andDo(print())
                .andExpect(status().isOk());
        verify(updateUserUseCase).updateUser(request);
    }

    @Test
    void findArtist() throws Exception {
        List<Playlist> playlists = new ArrayList<>();
        playlists.add(Playlist.builder().id(1L).userId(1L).title("Christmas Songs").build());
        playlists.add(Playlist.builder().id(2L).userId(2L).title("Summer Songs").build());
        GetArtistPageInfoRequest request = GetArtistPageInfoRequest.builder().id(2L).build();
        GetArtistPageInfoResponse response = GetArtistPageInfoResponse.builder().fName("Mariela").lName("Gocheva").playlists(playlists).build();
        when(getArtistPageInfoUseCase.findArtist(request)).thenReturn(response);
        mockMvc.perform(get("/users/artist/2")).andDo(print()).andExpect(status().isOk()).andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE)).andExpect(content().json("""
            {"fname":"Mariela","lname":"Gocheva", "playlists":[{"id": 1, "userId":  1, "title":  "Christmas Songs", "duration": null},{"id":2, "userId": 2, "title":"Summer Songs","duration": null}]}                              
        """));
        verify(getArtistPageInfoUseCase).findArtist(request);
    }
}