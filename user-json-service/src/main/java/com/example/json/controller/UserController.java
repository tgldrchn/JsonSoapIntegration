package com.example.json.controller;

import com.example.json.model.UserProfile;
import com.example.json.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Auth хэрэггүй — Register хийсний дараа profile үүсгэнэ
    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserProfile profile) {
        return ResponseEntity.status(201).body(userService.create(profile));
    }

    // Auth шаардлагатай
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable String id) {
        return userService.getById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id,
                                    @RequestBody UserProfile updated) {
        return ResponseEntity.ok(userService.update(id, updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
