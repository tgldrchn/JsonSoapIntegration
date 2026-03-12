package com.example.soap.model;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlRootElement(namespace = "http://example.com/auth", name = "RegisterResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class RegisterResponse {
    private boolean success;
    private String userId;
    private String message;
}