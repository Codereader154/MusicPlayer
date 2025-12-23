package com.example.MusicPlayer.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "Album_tbl")
@NoArgsConstructor
@AllArgsConstructor
public class AlbumDB {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Aid;

    @Column(name = "album_name",length = 45)
    private String album;

    @Column(name = "year",length = 15)
    private int year;

    private int createdById;

//    @OneToMany(mappedBy = "albumDB", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JsonIgnore
//    private List<SongDB> songs;

    /*@OneToOne
    @JoinColumn(name = "created_by")
    private UserDB userDB;*/




}
