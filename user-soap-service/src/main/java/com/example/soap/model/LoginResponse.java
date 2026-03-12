package com.example.soap.model;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlRootElement(namespace = "http://example.com/auth", name = "LoginResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class LoginResponse {
    private String token;
    private String userId;
    private String message;
    private String role;
}