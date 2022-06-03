package com.example.acspring.controller;

import com.example.acspring.entity.UserEntity;
import com.example.acspring.exception.UserAlreadyExistException;
import com.example.acspring.exception.UserNotFoundException;
import com.example.acspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/registration")
    public ResponseEntity registration(@RequestBody UserEntity user) {
        try {
            userService.registration(user);
            return ResponseEntity.ok("User was saved");
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping("/checkServer")
    public ResponseEntity checkServer() {
        try {
            return ResponseEntity.ok("Server is work");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping("/getUser")
    public ResponseEntity getOneUser(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(userService.getOne(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    /**
     * Delete by id
     * @param id id
     * @return response entity
     */
    @DeleteMapping("/deleteById/{id}") // @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.deleteUser(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    /**
     * Delete by username
     * @param username username
     * @return response entity
     */
    @DeleteMapping("/deleteByName/{username}")
    public ResponseEntity deleteUserBy(@PathVariable String username) {
        try {
            return ResponseEntity.ok(userService.deleteUser(username));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
}
