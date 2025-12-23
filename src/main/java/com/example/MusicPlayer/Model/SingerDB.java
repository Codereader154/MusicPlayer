package com.example.MusicPlayer.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "singer_tbl")
public class SingerDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int singerId;

    @Column(name = "SName",nullable = false)
    private String singerName;
}

