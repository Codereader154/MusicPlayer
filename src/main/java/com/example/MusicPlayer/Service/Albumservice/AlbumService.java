package com.example.MusicPlayer.Service.Albumservice;


import com.example.MusicPlayer.Model.AlbumDB;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
public interface AlbumService {


    AlbumDB saveAlbum(AlbumDB album);

    List<AlbumDB> getAllAlbums();

    Optional<AlbumDB> getAlbumById(int Aid);

    AlbumDB updateAlbum(int Aid, AlbumDB updatedAlbum);

    void deleteAlbum(int Aid);
}
