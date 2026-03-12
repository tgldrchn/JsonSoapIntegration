package com.example.soap.model;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlRootElement(namespace = "http://example.com/auth", name = "RegisterRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class RegisterRequest {

    @XmlElement(name = "username", namespace = "http://example.com/auth")
    private String username;

    @XmlElement(name = "password", namespace = "http://example.com/auth")
    private String password;
}