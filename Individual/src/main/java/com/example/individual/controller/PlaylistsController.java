package com.example.individual.controller;

import com.example.individual.business.*;
import com.example.individual.domain.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@RestController
@RequestMapping("/playlists")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
@ServerEndpoint(value = "/users/update")
public class PlaylistsController {
    private final GetAllPlaylistsByUserIdUseCase getAllPlaylistsByUserIdUseCase;

    private final GetAllPlaylistsUseCase getAllPlaylistsUseCase;
    private final GetPlaylistSongsUseCase getPlaylistSongsUseCase;
    private final CreatePlaylistUseCase createPlaylistUseCase;

    @Autowired
    private final AddSongUseCase addSongUseCase;

    @GetMapping("{userId}")
    public ResponseEntity<GetAllPlaylistsByUserIdResponse> getAllPlaylistsByUserId(@PathVariable (value = "userId") long userId) {
        GetAllPlaylistsByUserIdRequest request = GetAllPlaylistsByUserIdRequest.builder().userId(userId).build();
        GetAllPlaylistsByUserIdResponse response = getAllPlaylistsByUserIdUseCase.getPlaylists(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CreatePlaylistResponse> createPlaylist(@RequestBody CreatePlaylistRequest request) throws Exception {
//        CreatePlaylistRequest request = CreatePlaylistRequest.builder().userId(Long.parseLong(userId)).name(name).build();
        CreatePlaylistResponse response = createPlaylistUseCase.createPlaylist(request);
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

    @MessageMapping("/update")
    @SendTo("/topic/update")
    public ArtistUpdate update(WsMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new ArtistUpdate("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
}
