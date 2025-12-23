package com.example.MusicPlayer.Service.Userservice;

import com.example.MusicPlayer.Model.UserDB;
import com.example.MusicPlayer.Repository.UserDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDBRepository userRepository ;

    @Override
    public Optional<UserDB> getUserById(int userId) {
        return userRepository.findById(userId);
    }

    @Override
    public boolean isSuperAdmin(int userId) {
        if (userId <= 0) {
            return false;
        }else{
            Optional<UserDB> userOpt = userRepository.findById(userId);
        //return userOpt.map(userDB -> userDB.getRoleDB().getRoleId()==1).orElse(false);
            if(userOpt.get().getRoleDB().getRoleId()==1) {
                return true;
            }else{
                return false;
            }
        }
        /*else{
            if (userId == 101){
                return true;
            }else{
                return false;
        }
    }*/}

    @Override
    public UserDB saveUser(UserDB user) {
        return userRepository.save(user);
    }

    @Override
    public List<UserDB> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }
}
