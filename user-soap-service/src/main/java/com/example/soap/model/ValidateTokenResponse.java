package com.example.soap.model;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlRootElement(namespace = "http://example.com/auth", name = "ValidateTokenResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class ValidateTokenResponse {
    private boolean valid;
    private String userId;
}