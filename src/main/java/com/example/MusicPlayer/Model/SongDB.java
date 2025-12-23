package com.example.MusicPlayer.Model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "songs_tbl")
public class SongDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer songId;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "album_id", referencedColumnName = "Aid", nullable = false)
    private AlbumDB albumDB;

    @ManyToOne
    @JoinColumn(name = "singer_id", referencedColumnName = "singerId", nullable = false)
    private SingerDB singerDB;

}

