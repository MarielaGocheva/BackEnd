package com.example.individual.controller;

import com.example.individual.business.AddSongUseCase;
import com.example.individual.business.SwitchSongPositionUseCase;
import com.example.individual.domain.AddSongRequest;
import com.example.individual.domain.AddSongResponse;
import com.example.individual.domain.SwitchSongPositionResponse;
import com.example.individual.domain.SwitchSongPositionRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/songs")
@CrossOrigin("http://localhost:3000")
@AllArgsConstructor
public class SongController {
    private final AddSongUseCase addSongUseCase;
    private final SwitchSongPositionUseCase switchSongPositionUseCase;
    @PostMapping
    public ResponseEntity<AddSongResponse> addSong(@RequestBody @Valid AddSongRequest request) {
//        AddSongRequest request = AddSongRequest.builder().playlistId(playlistId).songUri(songUri).build();
        AddSongResponse response = addSongUseCase.addSong(request);
//        if (response != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
//        }
//        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<SwitchSongPositionResponse> switchPosition(@RequestParam(value = "playlistId", required = false) long playlistId,
                                                                     @RequestParam (value = "songPosition", required = false) int position){
        SwitchSongPositionRequest request = SwitchSongPositionRequest.builder().playlistId(playlistId).position(position).build();
        SwitchSongPositionResponse response = switchSongPositionUseCase.switchPosition(request);
        return  ResponseEntity.ok(response);
    }
}
