package com.example.individual.controller;

import com.example.individual.business.GetAllPlaylistsByUserIdUseCase;
import com.example.individual.domain.GetAllPlaylistsRequest;
import com.example.individual.domain.GetAllPlaylistsResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/playlists")
@AllArgsConstructor
public class PlaylistsController {
    private final GetAllPlaylistsByUserIdUseCase getAllPlaylistsByUserIdUseCase;

    @GetMapping
    public ResponseEntity<GetAllPlaylistsResponse> getAllPlaylistsByUserId(@RequestParam(value = "userId", required = false) long userId) {
        GetAllPlaylistsRequest request = GetAllPlaylistsRequest.builder().userId(userId).build();
        GetAllPlaylistsResponse response = getAllPlaylistsByUserIdUseCase.getPlaylists(request);
        return ResponseEntity.ok(response);
    }
}
