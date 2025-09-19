package com.shiva.bookstore.controller;

import com.shiva.bookstore.dto.SimpleResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {
    @GetMapping
    public ResponseEntity<?> root(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(new SimpleResponse(true, "Hello there, Our Api is live and your session id is "+request.getSession().getId()));
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }
    
}
