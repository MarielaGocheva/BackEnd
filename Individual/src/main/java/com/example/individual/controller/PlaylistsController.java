package com.example.individual.controller;

import com.example.individual.business.*;
import com.example.individual.business.exceptions.InsufficientNumberOfLikedPlaylists;
import com.example.individual.configuration.security.isAuthenticated.IsAuthenticated;
import com.example.individual.domain.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import javax.validation.UnexpectedTypeException;
import javax.validation.Valid;
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
    private final GetGenresUseCase getGenresUseCase;
    private final SearchUseCase searchUseCase;
    private final GetPlaylistGenresUseCase getPlaylistGenresUseCase;
    private final GetPlaylistUseCase getPlaylistUseCase;
    private final SetRecentlyPlayedUseCase setRecentlyPlayedUseCase;
    private final GetRecentlyPlayedUseCase getRecentlyPlayedUseCase;
    private final GetHistoryUseCase getHistoryUseCase;
    private final SetPlayedUseCase setPlayedUseCase;
    private final GetMostPlayedPlaylistsUseCase getMostPlayedPlaylistsUseCase;
    private final LikePlaylistUseCase likePlaylistUseCase;
    private final DislikePlaylistUseCase dislikePlaylistUseCase;
    private final CheckIfPlaylistIsLikedUseCase checkIfPlaylistIsLikedUseCase;
    private final GetRecommendationsUseCase getRecommendationsUseCase;

    @GetMapping("{userId}")
    public ResponseEntity<GetAllPlaylistsByUserIdResponse> getAllPlaylistsByUserId(@PathVariable (value = "userId") long userId) {
        GetAllPlaylistsByUserIdRequest request = GetAllPlaylistsByUserIdRequest.builder().userId(userId).build();
        GetAllPlaylistsByUserIdResponse response = getAllPlaylistsByUserIdUseCase.getPlaylists(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping(consumes = {"application/json"})
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

    @DeleteMapping("{playlistId}")
    public ResponseEntity<DeletePlaylistResponse> deletePlaylist(@PathVariable (value="playlistId") Long playlistId){
        DeletePlaylistRequest request = DeletePlaylistRequest.builder().playlistId(playlistId).build();
        DeletePlaylistResponse response = deletePlaylistUseCase.deletePlaylist(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/genres")
    public ResponseEntity<GetGenresResponse> getGenres(){
        GetGenresResponse response = getGenresUseCase.getGenres();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search/{searchItem}")
    public ResponseEntity<GetSearchResultsResponse> getResults(@PathVariable (value = "searchItem") String searchItem){
        GetSearchResultsRequest request = GetSearchResultsRequest.builder().searchItem(searchItem).build();
        GetSearchResultsResponse response = searchUseCase.getResults(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/genres/{playlistId}")
    public ResponseEntity<GetPlaylistGenresResponse> getGenres(@PathVariable (value = "playlistId") Long id){
        GetPlaylistGenresRequest request = GetPlaylistGenresRequest.builder().playlistId(id).build();
        GetPlaylistGenresResponse response = getPlaylistGenresUseCase.getGenres(request);
        return  ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/recentlyPlayed")
    public ResponseEntity<SetRecentlyPlayedResponse> setRecentlyPlayed(@RequestBody @Valid SetRecentlyPlayedRequest request){
        SetRecentlyPlayedResponse response = setRecentlyPlayedUseCase.setRecentlyPlayed(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/recentlyPlayed/{userId}")
    public ResponseEntity<GetRecentlyPlayedResponse> getRecentlyPlayed(@PathVariable (value = "userId") Long userId){
        GetRecentlyPlayedRequest request = GetRecentlyPlayedRequest.builder().userId(userId).build();
        GetRecentlyPlayedResponse response = getRecentlyPlayedUseCase.getRecentlyPlayed(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<GetHistoryResponse> getHistory(@PathVariable (value = "userId") Long userId){
        GetHistoryRequest request = GetHistoryRequest.builder().userId(userId).build();
        GetHistoryResponse response = getHistoryUseCase.getHistory(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/played")
    public ResponseEntity<Void> setPlayed(@RequestBody @Valid SetPlayedRequest request){
//        try {
            setPlayedUseCase.setPlayed(request);
            return ResponseEntity.noContent().build();
//        }
//        catch (Exception e){
//            throw new UnexpectedTypeException();
//        }
    }

    @GetMapping("/mostPlayed")
    public ResponseEntity<GetMostPlayedPlaylistsResponse> getMostPlayed(){
        try {
            GetMostPlayedPlaylistsResponse response = getMostPlayedPlaylistsUseCase.getMostPlayed();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (Exception e){
            throw new UnexpectedTypeException();
        }
    }

    @PostMapping("/likePlaylist")
    public ResponseEntity<Void> likePlaylist(@RequestBody @Valid LikePlaylistRequest request){
        try{
            likePlaylistUseCase.likePlaylist(request);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e){
            throw new UnexpectedTypeException();
        }
    }

    @DeleteMapping("/dislikePlaylist/{userId}/{playlistId}")
    public ResponseEntity<Void> dislikePlaylist(@PathVariable (value = "userId") Long userId,
                                                @PathVariable (value = "playlistId") Long playlistId){
//        try {
            DislikePlaylistRequest request = DislikePlaylistRequest.builder().userId(userId).playlistId(playlistId).build();
            dislikePlaylistUseCase.dislikePlaylist(request);
            return ResponseEntity.noContent().build();
//        }
//        catch (Exception e){
//            throw new UnexpectedTypeException();
//        }
    }

    @GetMapping("/isLiked/{userId}/{playlistId}")
    public ResponseEntity<CheckIfPlaylistIsLikedResponse> checkIfLiked(@PathVariable (value = "userId") Long userId,
                                                                       @PathVariable (value = "playlistId") Long playlistId){
        CheckIfPlaylistIsLikedRequest request = CheckIfPlaylistIsLikedRequest.builder().userId(userId).playlistId(playlistId).build();
        CheckIfPlaylistIsLikedResponse response = checkIfPlaylistIsLikedUseCase.checkIfLiked(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/getRecommendation/{userId}")
    public ResponseEntity<GetRecommendationsResponse> getRecommendations(@PathVariable (value = "userId") Long userId){
        try {
            GetRecommendationsRequest request = GetRecommendationsRequest.builder().userId(userId).build();
            GetRecommendationsResponse response = getRecommendationsUseCase.getRecommendations(request);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (InsufficientNumberOfLikedPlaylists e){
            GetRecommendationsResponse response = GetRecommendationsResponse.builder().build();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

    }

    @MessageMapping("/update")
    @SendTo("/topic/update")
    public ArtistUpdate update(WsMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new ArtistUpdate(getPlaylistUseCase.getPlaylist(Long.parseLong(message.getName())));
    }
}
