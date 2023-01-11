package com.example.individual.controller;

import com.example.individual.business.*;
import com.example.individual.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PlaylistsController.class)
class PlaylistsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CreatePlaylistUseCase createPlaylistUseCase;
    @MockBean
    private GetAllPlaylistsByUserIdUseCase getAllPlaylistsByUserIdUseCase;
    @MockBean
    private GetAllPlaylistsUseCase getAllPlaylistsUseCase;
    @MockBean
    private GetPlaylistSongsUseCase getPlaylistSongsUseCase;
    @MockBean
    private GetPlaylistsByTitleAndUserIdUseCase getPlaylistsByTitleAndUserIdUseCase;
    @MockBean
    private DeletePlaylistUseCase deletePlaylistUseCase;
    @MockBean
    private SearchUseCase searchUseCase;
    @MockBean
    private GetGenresUseCase getGenresUseCase;
    @MockBean
    private GetPlaylistGenresUseCase getPlaylistGenresUseCase;
    @MockBean
    private  GetPlaylistUseCase getPlaylistUseCase;
    @MockBean
    private SetRecentlyPlayedUseCase setRecentlyPlayedUseCase;
    @MockBean
    private GetRecentlyPlayedUseCase getRecentlyPlayedUseCase;
    @MockBean
    private GetHistoryUseCase getHistoryUseCase;
    @MockBean
    private AccessTokenDecoder accessTokenDecoder;

    @MockBean
    private AddSongUseCase addSongUseCase;

    @Test
    void createPlaylist() throws Exception {
        CreatePlaylistRequest request = CreatePlaylistRequest.builder().userId(1L).name("Christmas Songs").build();
        Playlist playlist = Playlist.builder().title("Christmas Songs").userId(1L).build();
        CreatePlaylistResponse response = CreatePlaylistResponse.builder().playlist(playlist).build();
        when(createPlaylistUseCase.createPlaylist(request)).thenReturn(response);
        mockMvc.perform(post("/playlists")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                    {"userId": 1, "name": "Christmas Songs"}
                """)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                {"playlist":{"id":null,"userId":1,"title":"Christmas Songs","duration":null,"imageUrl":null,"songs":null}}
            """))
        ;
        verify(createPlaylistUseCase).createPlaylist(request);
    }

    @Test
    void getAllPlaylistsByUserId() throws Exception {
        GetAllPlaylistsByUserIdResponse response = GetAllPlaylistsByUserIdResponse.builder().playlists(List.of(
                Playlist.builder().id(1L).userId(1L).title("Christmas Songs").build()
        )).build();
        GetAllPlaylistsByUserIdRequest request = GetAllPlaylistsByUserIdRequest.builder().userId(1L).build();
        when(getAllPlaylistsByUserIdUseCase.getPlaylists(request)).thenReturn(response);
        mockMvc.perform(get("/playlists/1")).andDo(print()).andExpect(status().isOk()).andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE)).andExpect(content().json("""
        {"playlists":[{"id": 1, "userId":  1, "title":  "Christmas Songs", "duration": null}]}
        """));
        verify(getAllPlaylistsByUserIdUseCase).getPlaylists(request);
        verifyNoInteractions(getAllPlaylistsUseCase);
    }

    @Test
    void getAllPlaylists() throws Exception {
        GetAllPlaylistsResponse response = GetAllPlaylistsResponse.builder().playlists(List.of(
                Playlist.builder().id(1L).userId(1L).title("Christmas Songs").build(),
                Playlist.builder().id(2L).userId(2L).title("Summer Songs").build()
                )).build();
        when(getAllPlaylistsUseCase.getPlaylists()).thenReturn(response);
        mockMvc.perform(get("/playlists")).andDo(print()).andExpect(status().isOk()).andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE)).andExpect(content().json("""
            {"playlists":[{"id": 1, "userId":  1, "title":  "Christmas Songs", "duration": null},{"id":2, "userId": 2, "title":"Summer Songs","duration": null}]}
        """));
        verify(getAllPlaylistsUseCase).getPlaylists();
        verifyNoInteractions(getAllPlaylistsByUserIdUseCase);
    }

    @Test
    void getPlaylistSongs() throws Exception {
        GetPlaylistSongsResponse response = GetPlaylistSongsResponse.builder().songs(List.of(
                Song.builder().id(1L).songUri("http://spotify").build()
        )).build();
        GetPlaylistSongsRequest request = GetPlaylistSongsRequest.builder().playlistId(2L).build();
        when(getPlaylistSongsUseCase.getSongs(request)).thenReturn(response);
        mockMvc.perform(get("/playlists/playlistsSongs/2")).andDo(print()).andExpect(status().isOk()).andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE)).andExpect(content().json("""
        {"songs":[{"id": 1, "songUri": "http://spotify", "artist": null, "title": null, "duration": null, "imageUrl":  null}]}
        """));
        verify(getPlaylistSongsUseCase).getSongs(request);
        verifyNoInteractions(getAllPlaylistsUseCase);
    }

    @Test
    void findPlaylistByTitleAndUserId() throws Exception {
        List<Song> songs = new ArrayList<>();
        songs.add(Song.builder().songUri("http://spotifyURI").title("Let It Snow").build());
        GetPlaylistsByTitleAndUserIdResponse response = GetPlaylistsByTitleAndUserIdResponse.builder().playlist(Playlist.builder().title("Christmas").userId(2L).songs(songs).build()).build();
        GetPlaylistsByTitleAndUserIdRequest request = GetPlaylistsByTitleAndUserIdRequest.builder().userId(2L).title("Christmas").build();
        when(getPlaylistsByTitleAndUserIdUseCase.getPlaylists(request)).thenReturn(response);
        mockMvc.perform(get("/playlists/findPlaylist/Christmas/2")).andDo(print()).andExpect(status().isOk()).andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE)).andExpect(content().json("""
        {"playlist":{"id":null,"userId":2,"title":"Christmas","duration":null,"imageUrl":null,"songs":[{"id":null,"songUri":"http://spotifyURI","artist":null,"title":"Let It Snow","duration":null,"imageUrl":null}]}}
        """));
        verify(getPlaylistsByTitleAndUserIdUseCase).getPlaylists(request);
    }

    @Test
    void deletePlaylist() throws Exception {
//        DeletePlaylistRequest request = DeletePlaylistRequest.builder().playlistId(2L).build();
//        DeletePlaylistResponse response = DeletePlaylistResponse.builder().deleted(true).build();
//        when(deletePlaylistUseCase.deletePlaylist(request)).thenReturn(response);
//        mockMvc.perform(delete("/playlists")
//                        .contentType(APPLICATION_JSON_VALUE)
//                        .content("""
//                    {"playlistId": 2}
//                """)
//                )
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
//                .andExpect(content().json("""
//                {"deleted": true}
//            """));
//        verify(deletePlaylistUseCase).deletePlaylist(request);
    }
}