package com.example.individual.controller;

import com.example.individual.business.*;
import com.example.individual.domain.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.UnexpectedTypeException;
import javax.validation.Valid;

@RestController
@RequestMapping("/songs")
@CrossOrigin("http://localhost:3000")
@AllArgsConstructor
public class SongController {
    private final AddSongUseCase addSongUseCase;
    private final SwitchSongPositionUseCase switchSongPositionUseCase;
    private final DeleteSongUseCase deleteSongUseCase;
    private final SetSongPlayedUseCase setSongPlayedUseCase;
    private final GetMostPlayedSongsUseCase getMostPlayedSongsUseCase;
    @PostMapping
    public ResponseEntity<AddSongResponse> addSong(@RequestBody @Valid AddSongRequest request) {
        AddSongResponse response = addSongUseCase.addSong(request);
//        if (response != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
//        }
//        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<SwitchSongPositionResponse> switchPosition(@RequestBody @Valid SwitchSongPositionRequest request){
        SwitchSongPositionResponse response = switchSongPositionUseCase.switchPosition(request);
        return  ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping
    public ResponseEntity<DeleteSongResponse> deleteSong(@RequestBody @Valid DeleteSongRequest request){
        DeleteSongResponse response = deleteSongUseCase.deleteSong(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/played")
    public ResponseEntity<Void> setSongPlayed(@RequestBody @Valid SetSongPlayedRequest request){
        try {
            setSongPlayedUseCase.setSongPlayed(request);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e){
            throw new UnexpectedTypeException();
        }
    }

    @GetMapping("/mostPlayed")
    public ResponseEntity<GetMostPlayedSongsResponse> getMostPlayed(){
        try {
            GetMostPlayedSongsResponse response = getMostPlayedSongsUseCase.getMostPlayed();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (Exception e){
            throw new UnexpectedTypeException();
        }
    }
}
