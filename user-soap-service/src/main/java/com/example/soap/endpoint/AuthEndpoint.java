package com.example.soap.endpoint;

import com.example.soap.model.*;
import com.example.soap.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.*;

@Endpoint
public class AuthEndpoint {

    private static final String NAMESPACE = "http://example.com/auth";

    @Autowired
    private AuthService authService;

    @PayloadRoot(namespace = NAMESPACE, localPart = "RegisterRequest")
    @ResponsePayload
    public RegisterResponse register(@RequestPayload RegisterRequest req) {
        return authService.register(req);
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "LoginRequest")
    @ResponsePayload
    public LoginResponse login(@RequestPayload LoginRequest req) {
        return authService.login(req);
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "ValidateTokenRequest")
    @ResponsePayload
    public ValidateTokenResponse validateToken(@RequestPayload ValidateTokenRequest req) {
        return authService.validateToken(req);
    }
}