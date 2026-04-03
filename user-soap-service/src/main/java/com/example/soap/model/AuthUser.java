package com.example.soap.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;
import java.time.LocalDateTime;

@Document(collection = "auth_users")
@Data
@NoArgsConstructor
public class AuthUser {
    @Id
    private String id;

    @Indexed(unique = true)
    private String username;

    private String passwordHash;
    private String token;
    private String role;
    private LocalDateTime createdAt;
}