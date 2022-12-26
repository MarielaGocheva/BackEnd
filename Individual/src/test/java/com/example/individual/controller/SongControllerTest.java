package com.example.individual.controller;

import com.example.individual.business.*;
import com.example.individual.domain.AddSongRequest;
import com.example.individual.domain.AddSongResponse;
import com.example.individual.domain.GetPlaylistSongsResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
}