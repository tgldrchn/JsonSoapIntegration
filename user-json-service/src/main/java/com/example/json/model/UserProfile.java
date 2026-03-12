package com.example.json.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_profiles")
@Data
@NoArgsConstructor
public class UserProfile {

    @Id
    private String userId;

    private String name;
    private String email;

    @Column(columnDefinition = "TEXT")
    private String bio;

    private String phone;
    private String avatarUrl;

    @Column(updatable = true)
    private LocalDateTime updatedAt;
}