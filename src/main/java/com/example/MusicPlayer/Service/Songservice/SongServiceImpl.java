package com.example.MusicPlayer.Service.Songservice;

import com.example.MusicPlayer.Model.SongDB;
import com.example.MusicPlayer.Repository.SongDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService{
    @Autowired
    private SongDBRepository songRepository;

    @Override
    public SongDB saveSong(SongDB song) {
        return songRepository.save(song);
    }

    @Override
    public List<SongDB> getAllSongs() {
        return songRepository.findAll();
    }

    @Override
    public Optional<SongDB> getSongById(int songId) {
        return songRepository.findById(songId);
    }

    @Override
    public SongDB updateSong(int songId, SongDB updatedSong) {
        return songRepository.findById(songId)
                .map(song -> {
                    song.setTitle(updatedSong.getTitle());
                    // In a real application, you'd handle updating related AlbumDB and SingerDB IDs here
                    return songRepository.save(song);
                })
                .orElseThrow(() -> new RuntimeException("Song not found with id: " + songId));
    }

    @Override
    public void deleteSong(int songId) {
        songRepository.deleteById(songId);
    }
}
