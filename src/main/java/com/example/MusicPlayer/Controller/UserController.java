package com.example.MusicPlayer.Controller;

import com.example.MusicPlayer.Model.RoleDB;
import com.example.MusicPlayer.Model.UserDB;
import com.example.MusicPlayer.Repository.UserDBRepository;
import com.example.MusicPlayer.Service.Userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDBRepository repo;

    @GetMapping("/all")
    public ResponseEntity<List<UserDB>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/getby/{userId}")
    public ResponseEntity<UserDB> getUserById(@PathVariable int userId) {
        return userService.getUserById(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody List<UserDB> user)

    {
//        if (AuthUserId != 1) {
//            return new ResponseEntity<>("Access Denied: Only Superadmin (Role 1) can create users.", HttpStatus.FORBIDDEN);
//        }else{
            for (UserDB u : user) {
                userService.saveUser(u);
            }
            return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);

//        }
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable int userId,
                                        @RequestBody UserDB userDetails,
                                        @RequestParam("userDbId") int userDbId) {
        if (userDbId != userDetails.getUserId()) {
            return new ResponseEntity<>("Please check your user details.", HttpStatus.FORBIDDEN);
        }
        else{
            Optional<UserDB> userOpt = userService.getUserById(userId);
            if (userOpt.isPresent()) {
                UserDB user = userOpt.get();
                user.setUsername(userDetails.getUsername());
                user.setRoleDB(userDetails.getRoleDB());
                UserDB updatedUser = userService.saveUser(user);
                return ResponseEntity.ok(updatedUser);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam("UserId") int userId,
                                        @RequestParam("authUserId") int authUserId) {
        if (authUserId != 1) {
            return new ResponseEntity<>("Access Denied: Only Superadmin (Role 1) can delete users.", HttpStatus.BAD_REQUEST);
        }
        else {
            repo.deleteById(userId);
            //userService.deleteUser(userId);
            return ResponseEntity.noContent().build();
        }
    }
}
