package com.shiva.bookstore.service;

import com.shiva.bookstore.dto.LoginRequest;
import com.shiva.bookstore.dto.SimpleResponse;
import com.shiva.bookstore.entity.Users;
import com.shiva.bookstore.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepo userRepo;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtService jwtService;

    public Optional<String> login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        if(authentication.isAuthenticated()) {
            return Optional.of(jwtService.generateToken(loginRequest.getEmail()));
        }else{
            return Optional.empty();
        }
    }

    public SimpleResponse Register(Users user) {
        if(!user.isValid()){
            return new SimpleResponse(false, "invalid email or password");
        }
        Optional<Users> user1 = userRepo.findByEmail(user.getEmail());
        if(user1.isPresent()){
            return new SimpleResponse(false, "email already exists");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("USER");
        userRepo.save(user);
        return new SimpleResponse(true, "Register successfully");
    }
}
