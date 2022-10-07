package com.example.individual.controller;

import com.example.individual.business.GetAllPlaylistsByUserIdUseCase;
import com.example.individual.business.GetAllPlaylistsUseCase;
import com.example.individual.domain.GetAllPlaylistsByUserIdRequest;
import com.example.individual.domain.GetAllPlaylistsByUserIdResponse;
import com.example.individual.domain.GetAllPlaylistsResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playlists")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class PlaylistsController {
    private final GetAllPlaylistsByUserIdUseCase getAllPlaylistsByUserIdUseCase;

    private final GetAllPlaylistsUseCase getAllPlaylistsUseCase;

    @GetMapping("/byUser")
    public ResponseEntity<GetAllPlaylistsByUserIdResponse> getAllPlaylistsByUserId(@RequestParam(value = "userId", required = false) long userId) {
        GetAllPlaylistsByUserIdRequest request = GetAllPlaylistsByUserIdRequest.builder().userId(userId).build();
        GetAllPlaylistsByUserIdResponse response = getAllPlaylistsByUserIdUseCase.getPlaylists(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<GetAllPlaylistsResponse> getAllPlaylists() {
        GetAllPlaylistsResponse response = getAllPlaylistsUseCase.getPlaylists();
        return ResponseEntity.ok(response);
    }
}
