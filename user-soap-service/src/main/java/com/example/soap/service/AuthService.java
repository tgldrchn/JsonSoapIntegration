package com.example.soap.service;

import com.example.soap.model.*;
import com.example.soap.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private AuthUserRepository repo;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BCryptPasswordEncoder encoder;  // Bean-аас авна

    public RegisterResponse register(RegisterRequest req) {
        RegisterResponse res = new RegisterResponse();
        
        // Debug log нэмэх
        System.out.println("=== REGISTER CALLED ===");
        System.out.println("Username: " + req.getUsername());
        System.out.println("Password: " + req.getPassword());
        System.out.println("Encoder: " + encoder);

        if (repo.findByUsername(req.getUsername()).isPresent()) {
            res.setSuccess(false);
            res.setMessage("Username already exists");
            return res;
        }

        AuthUser user = new AuthUser();
        user.setUsername(req.getUsername());
        
        // Encoder null эсэхийг шалгах
        if (encoder == null) {
            System.out.println("ENCODER IS NULL!");
            res.setSuccess(false);
            res.setMessage("Encoder error");
            return res;
        }
        
        user.setPasswordHash(encoder.encode(req.getPassword()));
        user.setRole("USER");
        repo.save(user);

        res.setSuccess(true);
        res.setUserId(user.getId());
        res.setMessage("Registered successfully");
        return res;
    }

    public LoginResponse login(LoginRequest req) {
        LoginResponse res = new LoginResponse();

        System.out.println("=== LOGIN CALLED ===");
        System.out.println("Username: " + req.getUsername());
        System.out.println("Password: " + req.getPassword());

        Optional<AuthUser> userOpt = repo.findByUsername(req.getUsername());

        System.out.println("User found: " + userOpt.isPresent());
        if (userOpt.isPresent()) {
            System.out.println("Stored hash: " + userOpt.get().getPasswordHash());
            System.out.println("Password match: " +
                encoder.matches(req.getPassword(), userOpt.get().getPasswordHash()));
        }

        if (userOpt.isEmpty() ||
            !encoder.matches(req.getPassword(), userOpt.get().getPasswordHash())) {
            res.setMessage("Invalid credentials");
            return res;
        }

        AuthUser user = userOpt.get();
        String token = jwtUtil.generateToken(user.getId(), user.getUsername(), user.getRole());
        user.setToken(token);
        repo.save(user);

        res.setToken(token);
        res.setUserId(user.getId());
        res.setRole(user.getRole());
        res.setMessage("Login successful");
        return res;
    }

    public ValidateTokenResponse validateToken(ValidateTokenRequest req) {
        ValidateTokenResponse res = new ValidateTokenResponse();
        try {
            System.out.println("=== VALIDATE TOKEN CALLED ===");
            System.out.println("Token from request: [" + req.getToken() + "]");

            String userId = jwtUtil.validateAndGetUserId(req.getToken().trim());

            res.setValid(true);
            res.setUserId(userId);

            System.out.println("VALID TOKEN, userId = " + userId);
        } catch (Exception e) {
            res.setValid(false);
            System.out.println("=== VALIDATE ERROR ===");
            e.printStackTrace();
        }
        return res;
    }
}
