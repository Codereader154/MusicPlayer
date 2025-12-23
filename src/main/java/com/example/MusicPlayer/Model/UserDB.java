package com.example.MusicPlayer.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "user_tbl")
@NoArgsConstructor
@AllArgsConstructor
public class UserDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "username",length = 45)
    private String username;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "roleId")
    private RoleDB roleDB;

}
