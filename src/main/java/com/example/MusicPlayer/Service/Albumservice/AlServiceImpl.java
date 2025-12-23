package com.example.MusicPlayer.Service.Albumservice;

import com.example.MusicPlayer.Model.AlbumDB;
import com.example.MusicPlayer.Repository.AlbumDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlServiceImpl implements AlbumService {

    @Autowired
    private AlbumDBRepository albumRepository;


    @Override
    public AlbumDB saveAlbum(AlbumDB album) {
        return albumRepository.save(album);
    }

    @Override
    public List<AlbumDB> getAllAlbums() {
        return albumRepository.findAll();
    }

    @Override
    public Optional<AlbumDB> getAlbumById(int Aid) {
        return albumRepository.findById(Aid);
    }

    @Override
    public AlbumDB updateAlbum(int Aid, AlbumDB updatedAlbum) {
        return albumRepository.findById(Aid)
                .map(album -> {
                    album.setAlbum(updatedAlbum.getAlbum());
                    album.setYear(updatedAlbum.getYear());
                    return albumRepository.save(album);
                })
                .orElseThrow(() -> new RuntimeException("Album not found with id: " + Aid));
    }

    @Override
    public void deleteAlbum(int Aid) {
        albumRepository.deleteById(Aid);
    }
}