package com.shiva.bookstore.service;

import com.shiva.bookstore.dto.LoginRequest;
import com.shiva.bookstore.dto.SimpleResponse;
import com.shiva.bookstore.entity.User;
import com.shiva.bookstore.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepo userRepo;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public SimpleResponse login(LoginRequest loginRequest) {
        Optional<User> user = userRepo.findByEmail(loginRequest.getEmail());
        if (user.isPresent() && encoder.matches(loginRequest.getPassword(), user.get().getPassword())) {
            return new SimpleResponse(true, "Login Successful");
        }else{
            return new SimpleResponse(false, "Invalid email or password");
        }
    }

    public SimpleResponse Register(User user) {
        if(!user.isValid()){
            return new SimpleResponse(false, "invalid email or password");
        }
        Optional<User> user1 = userRepo.findByEmail(user.getEmail());
        if(user1.isPresent()){
            return new SimpleResponse(false, "email already exists");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("USER");
        userRepo.save(user);
        return new SimpleResponse(true, "Register successfully");
    }
}
