package com.example.MusicPlayer.Service.Singerservice;

import com.example.MusicPlayer.Model.SingerDB;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface SingerService {
    // CRUD Methods
    SingerDB saveSinger(SingerDB singer);

    ResponseEntity<?> getAllSingers();

    Optional<SingerDB> getSingerById(int singerId);

    SingerDB updateSinger(int singerId, SingerDB updatedSinger);

    void deleteSinger(int singerId);
}
