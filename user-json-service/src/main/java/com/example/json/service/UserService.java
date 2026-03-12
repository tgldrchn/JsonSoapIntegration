package com.example.json.service;

import com.example.json.model.UserProfile;
import com.example.json.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserProfileRepository repo;

    public UserProfile create(UserProfile profile) {
        profile.setUpdatedAt(LocalDateTime.now());
        return repo.save(profile);
    }

    public Optional<UserProfile> getById(String id) {
        return repo.findById(id);
    }

    public UserProfile update(String id, UserProfile updated) {
        updated.setUserId(id);
        updated.setUpdatedAt(LocalDateTime.now());
        return repo.save(updated);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}