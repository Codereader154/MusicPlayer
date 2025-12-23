package com.example.MusicPlayer.Repository;
import com.example.MusicPlayer.Model.AlbumDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


// JpaRepository<Entity_Type, Primary_Key_Type>
@Repository
public interface AlbumDBRepository extends JpaRepository<AlbumDB, Integer> {
    // Spring automatically provides:
    // save() -> C, U
    // findById() -> R (one)
    // findAll() -> R (all)
    // deleteById() -> D
}
