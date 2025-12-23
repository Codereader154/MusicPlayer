package com.example.MusicPlayer.Service.Roleservice;

import com.example.MusicPlayer.Model.RoleDB;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

//@Service
public interface RoleService {



    ResponseEntity<?> saveRole(RoleDB role);

    List<RoleDB> getAllRoles();

    Optional<RoleDB> getRoleById(int roleId);

//    void deleteRole(int roleId);
}
