package com.example.individual.controller;

import com.example.individual.business.AddSongUseCase;
import com.example.individual.business.DeleteSongUseCase;
import com.example.individual.business.SwitchSongPositionUseCase;
import com.example.individual.domain.*;
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
    private final DeleteSongUseCase deleteSongUseCase;
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
    public ResponseEntity<SwitchSongPositionResponse> switchPosition(@RequestBody @Valid SwitchSongPositionRequest request){
//        SwitchSongPositionRequest request = SwitchSongPositionRequest.builder().playlistId(playlistId).position(position).build();
        SwitchSongPositionResponse response = switchSongPositionUseCase.switchPosition(request);
        return  ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping
    public ResponseEntity<DeleteSongResponse> deleteSong(@RequestBody @Valid DeleteSongRequest request){
        DeleteSongResponse response = deleteSongUseCase.deleteSong(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
