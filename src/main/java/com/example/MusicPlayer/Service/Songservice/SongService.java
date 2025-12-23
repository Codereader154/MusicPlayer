package com.example.MusicPlayer.Service.Songservice;

import com.example.MusicPlayer.Model.SongDB;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface SongService {

    SongDB saveSong(SongDB song);

    List<SongDB> getAllSongs();

    Optional<SongDB> getSongById(int songId);

    SongDB updateSong(int songId, SongDB updatedSong);

    void deleteSong(int songId);
}
