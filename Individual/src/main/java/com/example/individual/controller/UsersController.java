package com.example.individual.controller;

import com.example.individual.business.*;
import com.example.individual.domain.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {

    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final GetUserUseCase getUserUseCase;
    private final GetUsersUseCase getUsersUseCase;

    @GetMapping("{id}")
    public ResponseEntity<GetUserResponse> getUser(@PathVariable(value = "id") long id,
                                                   @RequestBody GetUserRequest request) {
        request.setId(id);
        GetUserResponse response = getUserUseCase.getUser(request);
        if (response.equals(null)) {
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
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request) {
        CreateUserResponse response = createUserUseCase.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("id") long id,
                                              @RequestBody  UpdateUserRequest request) {
        request.setId(id);
        updateUserUseCase.updateUser(request);
        return ResponseEntity.noContent().build();
    }
}
