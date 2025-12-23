package com.example.MusicPlayer.Service.Roleservice;

import com.example.MusicPlayer.Model.RoleDB;
import com.example.MusicPlayer.Repository.RoleDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleDBRepository roleRepository;


    @Override
    public ResponseEntity<?> saveRole(RoleDB role) {
        roleRepository.save(role);
        return new ResponseEntity<>("Role created successfully", HttpStatus.CREATED);
    }
    @Override
    public List<RoleDB> getAllRoles() {
        return roleRepository.findAll();
    }
    @Override
    public Optional<RoleDB> getRoleById(int roleId) {
        return roleRepository.findById(roleId);
    }
//    @Override
//    public void deleteRole(int roleId) {
//        roleRepository.deleteById(roleId);
//    }
}
