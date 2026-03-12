package com.example.soap.model;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlRootElement(namespace = "http://example.com/auth", name = "ValidateTokenRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class ValidateTokenRequest {

    @XmlElement(name = "token", namespace = "http://example.com/auth")
    private String token;
}