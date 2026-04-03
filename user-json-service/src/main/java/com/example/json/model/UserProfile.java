package com.example.json.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "user_profiles")
@Data
@NoArgsConstructor
public class UserProfile {
    @Id
    private String userId;
    private String name;
    private String email;
    private String bio;
    private String phone;
    private String avatarUrl;
    private LocalDateTime updatedAt;
}