package com.shiva.bookstore.controller;

import com.shiva.bookstore.dto.LoginRequest;
import com.shiva.bookstore.dto.SimpleResponse;
import com.shiva.bookstore.entity.Users;
import com.shiva.bookstore.service.AuthService;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Users user){
        SimpleResponse res = authService.Register(user);
        System.out.println(res);
        return ResponseEntity.status(res.isSuccess()? HttpStatus.CREATED:HttpStatus.BAD_REQUEST).body(res);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<String> res = authService.login(loginRequest);

        if (res.isPresent()) {
            String token = res.get();

            // Create HttpOnly Cookie
            Cookie cookie = new Cookie("jwt", token);
            cookie.setHttpOnly(true);  // prevent JS access
            cookie.setSecure(false);   // set true if using HTTPS
            cookie.setPath("/");       // cookie available for all endpoints
            cookie.setMaxAge(24 * 60 * 60); // 1 Day (same as token)

            // Build response with cookie
            return ResponseEntity.ok()
                    .header("Set-Cookie", String.format("%s=%s; Max-Age=%d; Path=%s; HttpOnly",
                            cookie.getName(),
                            cookie.getValue(),
                            cookie.getMaxAge(),
                            cookie.getPath()))
                    .body("Login successful");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
}
