package com.shiva.bookstore.controller;

import com.shiva.bookstore.dto.LoginRequest;
import com.shiva.bookstore.dto.SimpleResponse;
import com.shiva.bookstore.entity.User;
import com.shiva.bookstore.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user){
        SimpleResponse res = authService.Register(user);
        System.out.println(res);
        return ResponseEntity.status(res.isSuccess()? HttpStatus.CREATED:HttpStatus.BAD_REQUEST).body(res);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        SimpleResponse res = authService.login(loginRequest);
        System.out.println(res);
        return ResponseEntity.ok().body(res);
    }
}
