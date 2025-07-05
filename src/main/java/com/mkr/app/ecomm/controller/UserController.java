package com.mkr.app.ecomm.controller;

import com.mkr.app.ecomm.dto.UserRequest;
import com.mkr.app.ecomm.dto.UserResponse;
import com.mkr.app.ecomm.model.User;
import com.mkr.app.ecomm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getUser() {
        return new ResponseEntity<>(userService.fetchAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> getUsers(@PathVariable Long id) {
        return userService.fetchUser(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestBody UserRequest user) {

        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id,
                                           @RequestBody UserRequest user) {
        boolean updated = userService.editUser(id, user);
        return updated ? ResponseEntity.ok("User updated successfully")
                : ResponseEntity.notFound().build();
    }
}
