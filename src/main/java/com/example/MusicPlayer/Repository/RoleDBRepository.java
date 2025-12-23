package com.example.MusicPlayer.Repository;

import com.example.MusicPlayer.Model.RoleDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDBRepository extends JpaRepository<RoleDB, Integer> {
    boolean existsByName(String name);
}
