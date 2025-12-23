package com.example.MusicPlayer.Controller;

import com.example.MusicPlayer.Model.AlbumDB;

import com.example.MusicPlayer.Repository.AlbumDBRepository;
import com.example.MusicPlayer.Service.Albumservice.AlbumService;
import com.example.MusicPlayer.Service.Userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private UserService userService;

    @Autowired
    private AlbumDBRepository repo;


    @GetMapping("/all")
    public ResponseEntity<List<AlbumDB>> getAllAlbums() {
        return ResponseEntity.ok(albumService.getAllAlbums());
    }

    @GetMapping("/getalbum/{Aid}")
    public ResponseEntity<AlbumDB> getAlbumById(@PathVariable int Aid) {
        return albumService.getAlbumById(Aid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/create")
    public ResponseEntity<?> createAlbum(@RequestBody List<AlbumDB> album,
                                         @RequestParam("authUserId") int authUserId) {
        if (authUserId != 1) {
            return new ResponseEntity<>("Access Denied: Only Superadmin (Role 1) can create albums.", HttpStatus.FORBIDDEN);
        }else{
            for(AlbumDB a: album){
                a.setCreatedById(authUserId);
                albumService.saveAlbum(a);
            }
            return new ResponseEntity<>("Album created successfully", HttpStatus.CREATED);
        }

    }


    @PutMapping("/update/{Aid}")
    public ResponseEntity<?> updateAlbum(@PathVariable int Aid,
                                         @RequestBody AlbumDB albumDetails,
                                         @RequestParam("authUserId") int authUserId) {
        if (authUserId!= 1) {
            return new ResponseEntity<>("Access Denied: Only Superadmin (Role 1) can update albums.", HttpStatus.FORBIDDEN);
        }
        else{
            AlbumDB updatedAlbum = albumService.updateAlbum(Aid, albumDetails);
            return ResponseEntity.ok(updatedAlbum);
        }
    }


    @DeleteMapping("/delete/{Aid}")
    public ResponseEntity<?> deleteAlbum(@PathVariable int Aid,
                                         @RequestParam("authUserId") int authUserId) {
        if (authUserId!=1) {
            return new ResponseEntity<>("Access Denied: Only Superadmin (Role 1) can delete albums.", HttpStatus.FORBIDDEN);
        }else{
            repo.deleteById(Aid);
            return ResponseEntity.ok("Album deleted successfully.");
        }
    }
}