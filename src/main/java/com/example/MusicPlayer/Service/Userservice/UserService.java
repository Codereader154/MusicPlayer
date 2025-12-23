package com.example.MusicPlayer.Service.Userservice;

import com.example.MusicPlayer.Model.UserDB;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<UserDB> getUserById(int userId);


    boolean isSuperAdmin(int userId);

    UserDB saveUser(UserDB user);

    List<UserDB> getAllUsers();

    void deleteUser(int userId);

    //boolean userExists(int userId);
}
