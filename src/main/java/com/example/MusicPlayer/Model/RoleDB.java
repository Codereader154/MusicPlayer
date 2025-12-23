package com.example.MusicPlayer.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "role_tbl")
@NoArgsConstructor
@AllArgsConstructor
public class RoleDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;

    @Column(name = "name", nullable = false)
    private String name;

//    @OneToMany
//    private UserDB userDB;
}
