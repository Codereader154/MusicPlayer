package com.example.MusicPlayer.Controller;

import com.example.MusicPlayer.Model.SingerDB;


import com.example.MusicPlayer.Service.Singerservice.SingerService;
import com.example.MusicPlayer.Service.Userservice.UserService;
;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/singers")
public class SingerController {

    @Autowired
    private SingerService singerService;

    @Autowired
    private UserService userService;


    @GetMapping("/all")
    public ResponseEntity<List<SingerDB>> getAllSingers() {
        return ResponseEntity.ok((List<SingerDB>) singerService.getAllSingers());
    }

    @GetMapping("/getby/{singerId}")
    public Optional<SingerDB> getSingerById(@PathVariable int singerId) {
        return singerService.getSingerById(singerId);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createSinger(@RequestBody List<SingerDB> singer,
                                          @RequestParam("authUserId") int authUserId) {
        if (authUserId != 1) {
            return new ResponseEntity<>("Access Denied: Only Superadmin (Role 1) can create singers.", HttpStatus.FORBIDDEN);
        }else{
            for(SingerDB s: singer){
                singerService.saveSinger(s);
            }
            return new ResponseEntity<>("Singer created successfully", HttpStatus.CREATED);}
    }

    @PutMapping("/update/{singerId}")
    public ResponseEntity<?> updateSinger(@PathVariable int singerId,
                                          @RequestBody SingerDB singerDetails,
                                          @RequestParam("authUserId") int authUserId) {
        if (authUserId != 1) {
            return new ResponseEntity<>("Access Denied: Only Superadmin (Role 1) can update singers.", HttpStatus.FORBIDDEN);
        }else{
            SingerDB updatedSinger = singerService.updateSinger(singerId, singerDetails);
            return ResponseEntity.ok(updatedSinger);}

    }

    @DeleteMapping("/delete/{singerId}")
    public ResponseEntity<?> deleteSinger(@PathVariable int singerId,
                                          @RequestParam("authUserId") int authUserId) {
        if (authUserId!=1) {
            return new ResponseEntity<>("Access Denied: Only Superadmin (Role 1) can delete singers.", HttpStatus.FORBIDDEN);
        }else {
            singerService.deleteSinger(singerId);
            return ResponseEntity.noContent().build();
        }
    }
}