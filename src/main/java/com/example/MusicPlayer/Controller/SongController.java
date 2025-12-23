package com.example.MusicPlayer.Controller;

import com.example.MusicPlayer.Model.AlbumDB;
import com.example.MusicPlayer.Model.SingerDB;
import com.example.MusicPlayer.Model.SongDB;

import com.example.MusicPlayer.Repository.AlbumDBRepository;
import com.example.MusicPlayer.Repository.SingerDBRepository;
import com.example.MusicPlayer.Service.Songservice.SongService;
import com.example.MusicPlayer.Service.Userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    @Autowired
    private SongService songService;

    @Autowired
    private UserService userService;

    @Autowired
    private AlbumDBRepository albumRepo;
    @Autowired
    private SingerDBRepository singerRepo;

    @GetMapping("/all")
    public ResponseEntity<List<SongDB>> getAllSongs() {
        return ResponseEntity.ok(songService.getAllSongs());
    }

    @GetMapping("/getby/{songId}")
    public ResponseEntity<SongDB> getSongById(@PathVariable int songId) {
        return songService.getSongById(songId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createSong(@RequestBody List<SongDB> song,
                                        @RequestParam("authUserId") int authUserId) {
        if (authUserId!=1) {
            return new ResponseEntity<>("Access Denied: Only Superadmin (Role 1) can create songs.", HttpStatus.FORBIDDEN);
        }else {
            for (SongDB s : song) {
                if (s.getAlbumDB() == null || s.getAlbumDB().getAid() == 0)
                    throw new RuntimeException("album is required");

                if (s.getSingerDB() == null || s.getSingerDB().getSingerId() == 0)
                    throw new RuntimeException("singer is required");
                AlbumDB album = albumRepo.findById(s.getAlbumDB().getAid())
                        .orElseThrow(() -> new RuntimeException("Album data not exists"));

                SingerDB singer = singerRepo.findById(s.getSingerDB().getSingerId())
                        .orElseThrow(() -> new RuntimeException("Singer data not exists"));
                s.setAlbumDB(album);
                s.setSingerDB(singer);

                songService.saveSong(s);
            }

            return new ResponseEntity<>("Song created successfully", HttpStatus.CREATED);
        }
    }

    @PutMapping("/update/{songId}")
    public ResponseEntity<?> updateSong(@PathVariable int songId,
                                        @RequestBody SongDB songDetails,
                                        @RequestParam("authUserId") int authUserId) {
        if (authUserId!=1) {
            return new ResponseEntity<>("Access Denied: Only Superadmin (Role 1) can update songs.", HttpStatus.FORBIDDEN);
        }
        else {
            SongDB updatedSong = songService.updateSong(songId, songDetails);
            return ResponseEntity.ok(updatedSong);
        }
    }

    @DeleteMapping("/delete/{songId}")
    public ResponseEntity<?> deleteSong(@PathVariable int songId,
                                        @RequestParam("authUserId") int authUserId) {
        if (authUserId != 1) {
            return new ResponseEntity<>("Access Denied: Only Superadmin (Role 1) can delete songs.", HttpStatus.FORBIDDEN);
        } else {
            songService.deleteSong(songId);
            return ResponseEntity.noContent().build();
        }
    }
}