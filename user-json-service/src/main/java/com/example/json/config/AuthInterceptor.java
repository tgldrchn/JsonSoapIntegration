package com.example.json.config;

import com.example.json.service.SoapClient;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private SoapClient soapClient;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            response.setStatus(401);
            response.getWriter().write("{\"error\":\"No token provided\"}");
            return false;
        }

        String token = header.substring(7).trim();
        String userId = soapClient.validateToken(token);

        if (userId == null) {
            response.setStatus(403);
            response.getWriter().write("{\"error\":\"Invalid token\"}");
            return false;
        }

      

        request.setAttribute("userId", userId);
        return true;
    }
}