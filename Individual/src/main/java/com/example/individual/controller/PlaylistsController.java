package com.example.individual.controller;

import com.example.individual.business.AddSongUseCase;
import com.example.individual.business.GetAllPlaylistsByUserIdUseCase;
import com.example.individual.business.GetAllPlaylistsUseCase;
import com.example.individual.business.GetPlaylistSongsUseCase;
import com.example.individual.domain.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playlists")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class PlaylistsController {
    private final GetAllPlaylistsByUserIdUseCase getAllPlaylistsByUserIdUseCase;

    private final GetAllPlaylistsUseCase getAllPlaylistsUseCase;
    private final GetPlaylistSongsUseCase getPlaylistSongsUseCase;
    //check
    @Autowired
    private final AddSongUseCase addSongUseCase;

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

    @GetMapping("/playlistsSongs")
    public ResponseEntity<GetPlaylistSongsResponse> getPlaylistSongs(@RequestParam (value = "playlistId", required = false) long playlistId) {
        GetPlaylistSongsRequest request = GetPlaylistSongsRequest.builder().playlistId(playlistId).build();
        GetPlaylistSongsResponse response = getPlaylistSongsUseCase.getSongs(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/addSong")
    public ResponseEntity<AddSongResponse> addSong(@RequestParam (value = "playlistId", required = false) long playlistId,
                                                   @RequestParam (value = "songUri", required = false) String songUri) {
        AddSongRequest request = AddSongRequest.builder().playlistId(playlistId).songUri(songUri).build();
        AddSongResponse response = addSongUseCase.addSong(request);
        return ResponseEntity.ok(response);
    }
}
