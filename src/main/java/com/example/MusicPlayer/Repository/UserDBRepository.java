package com.example.MusicPlayer.Repository;

import com.example.MusicPlayer.Model.UserDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDBRepository extends JpaRepository<UserDB, Integer> {

    void deleteById(int userId);
}
