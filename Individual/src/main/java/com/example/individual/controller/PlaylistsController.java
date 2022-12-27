package com.example.individual.controller;

import com.example.individual.business.*;
import com.example.individual.domain.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.validation.Valid;
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
    private final GetPlaylistsByTitleAndUserIdUseCase getPlaylistsByTitleAndUserIdUseCase;
    private final DeletePlaylistUseCase deletePlaylistUseCase;

    @GetMapping("{userId}")
    public ResponseEntity<GetAllPlaylistsByUserIdResponse> getAllPlaylistsByUserId(@PathVariable (value = "userId") long userId) {
        GetAllPlaylistsByUserIdRequest request = GetAllPlaylistsByUserIdRequest.builder().userId(userId).build();
        GetAllPlaylistsByUserIdResponse response = getAllPlaylistsByUserIdUseCase.getPlaylists(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CreatePlaylistResponse> createPlaylist(@RequestBody @Valid CreatePlaylistRequest request) throws Exception {
//        CreatePlaylistRequest request = CreatePlaylistRequest.builder().userId(Long.parseLong(userId)).name(name).build();
        CreatePlaylistResponse response = createPlaylistUseCase.createPlaylist(request);
        if (response != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<GetAllPlaylistsResponse> getAllPlaylists() {
        GetAllPlaylistsResponse response = getAllPlaylistsUseCase.getPlaylists();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/playlistsSongs/{id}")
    public ResponseEntity<GetPlaylistSongsResponse> getPlaylistSongs(@PathVariable (value = "id") Long playlistId) {
        GetPlaylistSongsRequest request = GetPlaylistSongsRequest.builder().playlistId(playlistId).build();
        GetPlaylistSongsResponse response = getPlaylistSongsUseCase.getSongs(request);
        if (response != null) {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("findPlaylist/{title}/{userId}")
    public ResponseEntity<GetPlaylistsByTitleAndUserIdResponse> getPlaylists(@PathVariable (value = "title") String title, @PathVariable (value = "userId") long userId){
        GetPlaylistsByTitleAndUserIdRequest request = GetPlaylistsByTitleAndUserIdRequest.builder().title(title).userId(userId).build();
        GetPlaylistsByTitleAndUserIdResponse response = getPlaylistsByTitleAndUserIdUseCase.getPlaylists(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<DeletePlaylistResponse> deletePlaylist(@RequestBody @Valid DeletePlaylistRequest request){
        DeletePlaylistResponse response = deletePlaylistUseCase.deletePlaylist(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @MessageMapping("/update")
    @SendTo("/topic/update")
    public ArtistUpdate update(WsMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new ArtistUpdate("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
}
