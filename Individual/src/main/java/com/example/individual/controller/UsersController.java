package com.example.individual.controller;

import com.example.individual.business.*;
import com.example.individual.configuration.security.isAuthenticated.IsAuthenticated;
import com.example.individual.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:3000")
@RequiredArgsConstructor
public class UsersController {

    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final GetUserUseCase getUserUseCase;
    private final GetUsersUseCase getUsersUseCase;
    private final GetArtistPageInfoUseCase getArtistPageInfoUseCase;

    @GetMapping("{id}")
    public ResponseEntity<GetUserResponse> getUser(@PathVariable (value="id") Long id) {
        GetUserRequest request = GetUserRequest.builder().id(id).build();
        GetUserResponse response = getUserUseCase.getUser(request);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<GetAllUsersResponse> getAllUsers() {
        GetAllUsersRequest request = GetAllUsersRequest.builder().build();
        GetAllUsersResponse response = getUsersUseCase.getUsers(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest request) {
        CreateUserResponse response = createUserUseCase.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("id") Long id,
                                              @RequestBody  UpdateUserRequest request) {
//        request.setId(id);
        updateUserUseCase.updateUser(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @IsAuthenticated
    @GetMapping("/artist/{id}")
    public ResponseEntity<GetArtistPageInfoResponse> findArtist (@PathVariable (value = "id") Long id) {
        GetArtistPageInfoRequest request = GetArtistPageInfoRequest.builder().id(id).build();
        GetArtistPageInfoResponse response = getArtistPageInfoUseCase.findArtist(request);
        return ResponseEntity.ok(response);
    }
}
