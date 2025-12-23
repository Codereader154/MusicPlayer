package com.example.MusicPlayer.Repository;

import com.example.MusicPlayer.Model.SingerDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SingerDBRepository extends JpaRepository<SingerDB, Integer> {

}
