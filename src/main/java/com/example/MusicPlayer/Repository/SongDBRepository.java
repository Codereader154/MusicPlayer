package com.example.MusicPlayer.Repository;

import com.example.MusicPlayer.Model.SongDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongDBRepository extends JpaRepository<SongDB, Integer> {
}
