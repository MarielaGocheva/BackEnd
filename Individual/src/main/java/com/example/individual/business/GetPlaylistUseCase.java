package com.example.individual.business;

import com.example.individual.domain.ArtistUpdate;
import com.example.individual.domain.Playlist;

public interface GetPlaylistUseCase {
    Playlist getPlaylist(Long playlistId);
}
