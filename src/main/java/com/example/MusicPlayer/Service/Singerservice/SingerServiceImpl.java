package com.example.MusicPlayer.Service.Singerservice;

import com.example.MusicPlayer.Model.SingerDB;
import com.example.MusicPlayer.Repository.SingerDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SingerServiceImpl implements SingerService{

    @Autowired
    private SingerDBRepository singerRepository;

    @Override
    public SingerDB saveSinger(SingerDB singer) {
        return singerRepository.save(singer);
    }

    @Override
    public ResponseEntity<?> getAllSingers() {
        return (ResponseEntity<?>) singerRepository.findAll();
    }

    @Override
    public Optional<SingerDB> getSingerById(int singerId) {
        return singerRepository.findById(singerId);
    }

    @Override
    public SingerDB updateSinger(int singerId, SingerDB updatedSinger) {
        return singerRepository.findById(singerId)
                .map(singer -> {
                    singer.setSingerName(updatedSinger.getSingerName());
                    return singerRepository.save(singer);
                })
                .orElseThrow(() -> new RuntimeException("Singer not found with id: " + singerId));
    }

    @Override
    public void deleteSinger(int singerId) {
        singerRepository.deleteById(singerId);
    }
}
