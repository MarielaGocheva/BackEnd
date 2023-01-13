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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SongController.class)
class SongControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AddSongUseCase addSongUseCase;
    @MockBean
    private SwitchSongPositionUseCase switchSongPositionUseCase;
    @MockBean
    private DeleteSongUseCase deleteSongUseCase;
    @MockBean
    private SetSongPlayedUseCase setSongPlayedUseCase;
    @MockBean
    private GetMostPlayedSongsUseCase getMostPlayedSongsUseCase;
    @MockBean
    private AccessTokenDecoder accessTokenDecoder;

    @Test
    void addSong() throws Exception {
        AddSongRequest request = AddSongRequest.builder().playlistId(4L).songUri("http://spotifyURI").artist("Alter Bridge").img("img").title("The Uninvited").build();
        AddSongResponse response = AddSongResponse.builder().added(true).build();
        when(addSongUseCase.addSong(request)).thenReturn(response);
        mockMvc.perform(post("/songs")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                    {"playlistId": 4, "songUri": "http://spotifyURI", "artist": "Alter Bridge", "title": "The Uninvited", "img": "img"}
                """)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                {"added": true}
            """))
        ;
        verify(addSongUseCase).addSong(request);
    }

    @Test
    void switchPosition() throws Exception {
        SwitchSongPositionRequest request = SwitchSongPositionRequest.builder().position(2).playlistId(2L).build();
        List<Song> reordered = new ArrayList<>();
        reordered.add(Song.builder().songUri("http://spotify").title("Shadow of the Sun").artist("Audioslave").build());
        SwitchSongPositionResponse response = SwitchSongPositionResponse.builder().songs(reordered).build();
        when(switchSongPositionUseCase.switchPosition(request)).thenReturn(response);
        mockMvc.perform(put("/songs")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                    {"playlistId": 2, "position": 2}
                """)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                {"songs":[{"id":null,"songUri":"http://spotify","artist":"Audioslave","title":"Shadow of the Sun","duration":null,"imageUrl":null}]}
            """))
        ;
        verify(switchSongPositionUseCase).switchPosition(request);
    }

    @Test
    void deleteSong() throws Exception {
        DeleteSongRequest request = DeleteSongRequest.builder().playlistId(2L).songUri("http://spotify").build();
        DeleteSongResponse response = DeleteSongResponse.builder().deleted(true).build();
        when(deleteSongUseCase.deleteSong(request)).thenReturn(response);
        mockMvc.perform(delete("/songs")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                    {"playlistId": 2, "songUri": "http://spotify"}
                """)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                {"deleted": true}
            """))
        ;
        verify(deleteSongUseCase).deleteSong(request);
    }
}