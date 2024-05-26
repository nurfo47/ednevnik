package com.tfb.ednevnik.service.serviceImpl;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        
        var authourities = authentication.getAuthorities();
        var roles = authourities.stream().map(r -> r.getAuthority()).findFirst();

        if (roles.orElse("").equals("ADMIN")){
            response.sendRedirect("/admin-dashboard");
        } else if (roles.orElse("").equals("PROFESOR")){
            response.sendRedirect("/profesor-dashboard");
        } else if (roles.orElse("").equals("UCENIK")){
            response.sendRedirect("/user-dashboard"); 
        } else {
            response.sendRedirect("/error");
        }
    }
    
}
