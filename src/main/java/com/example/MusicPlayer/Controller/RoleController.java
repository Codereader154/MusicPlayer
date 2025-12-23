package com.example.MusicPlayer.Controller;

import com.example.MusicPlayer.Model.RoleDB;
import com.example.MusicPlayer.Repository.RoleDBRepository;
import com.example.MusicPlayer.Service.Roleservice.RoleService;
import com.example.MusicPlayer.Service.Userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleDBRepository repo;

    @GetMapping("/all")
    public ResponseEntity<List<RoleDB>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @GetMapping("/getby/{roleId}")
    public ResponseEntity<RoleDB> getRoleById(@PathVariable int roleId) {
        return roleService.getRoleById(roleId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/create")
    public ResponseEntity<?> createRole(@RequestBody List <RoleDB> role,
                                        @RequestParam("authUserId") int authUserId) {
        if (authUserId != 1) {
            return new ResponseEntity<>("Access Denied: Only Superadmin (Role 1) can create roles.", HttpStatus.FORBIDDEN);
        }else{
            for (RoleDB r : role) {
                roleService.saveRole(r);
            }
            return new ResponseEntity<>("Role created successfully", HttpStatus.CREATED);
        }

    }


    @PutMapping("/update/{roleId}")
    public ResponseEntity<?> updateRole(@PathVariable int roleId,
                                        @RequestBody RoleDB role,
                                        @RequestParam("authUserId") int authUserId) {
        if (authUserId != 1) {
            return new ResponseEntity<>("Access Denied: Only Superadmin (Role 1) can update roles.", HttpStatus.FORBIDDEN);
        }else{
            role.setRoleId(role.getRoleId());
            role.setName(role.getName());
            return roleService.saveRole(role);
        }
    }

    @DeleteMapping("/delete/{roleId}")
    public ResponseEntity<?> deleteRole(@PathVariable int roleId,
                                        @RequestParam("authUserId") int authUserId) {
        if (authUserId != 1) {
            return new ResponseEntity<>("Access Denied: Only Superadmin (Role 1) can delete roles.", HttpStatus.FORBIDDEN);
        }else{
            repo.deleteById(roleId);
            return new ResponseEntity<>("Access Granted: Superadmin (Role 1) have deleted role.{roleId}", HttpStatus.FORBIDDEN);
        }

//    @PutMapping("/update/{roleId}")
//    public ResponseEntity<?> updateRole(@PathVariable int roleId,
//                                        @RequestBody RoleDB roleDetails,
//                                        @RequestParam("authUserId") int authUserId) {
//        if (!userService.isSuperAdmin(authUserId)) {
//            return new ResponseEntity<>("Access Denied: Only Superadmin (Role 1) can update roles.", HttpStatus.FORBIDDEN);
//        }
//        try {
//            roleDetails.setRoleId(roleId);
//            return ResponseEntity.ok(roleService.saveRole(roleDetails));
//        } catch (RuntimeException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        }
//    }

//    @DeleteMapping("/delete/{roleId}")
//    public ResponseEntity<?> deleteRole(@PathVariable int roleId,
//                                        @RequestParam("authUserId") int authUserId) {
//        if (!userService.isSuperAdmin(authUserId)) {
//            return new ResponseEntity<>("Access Denied: Only Superadmin (Role 1) can delete roles.", HttpStatus.FORBIDDEN);
//        }
//        roleService.deleteRole(roleId);
//        return ResponseEntity.noContent().build();
//    }
}}
