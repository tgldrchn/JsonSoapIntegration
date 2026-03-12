package com.example.soap.model;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlRootElement(namespace = "http://example.com/auth", name = "LoginRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class LoginRequest {

    @XmlElement(name = "username", namespace = "http://example.com/auth")
    private String username;

    @XmlElement(name = "password", namespace = "http://example.com/auth")
    private String password;
}