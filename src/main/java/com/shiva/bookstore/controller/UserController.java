package com.shiva.bookstore.controller;

import com.shiva.bookstore.dto.ApiResponse;
import com.shiva.bookstore.dto.SimpleResponse;
import com.shiva.bookstore.dto.UpdatePassword;
import com.shiva.bookstore.entity.Users;
import com.shiva.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<?> UserProfile(Authentication authentication) {
        Users user = userService.getUserProfile(authentication.getName());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new SimpleResponse(false, "No User Profile Found."));
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(true, "User Profile Found", user));
        }
    }

    @PostMapping("/update/password")
    public ResponseEntity<?> updatePassword(Authentication authentication, @RequestBody UpdatePassword updatePassword) {
        Users user = userService.getUserProfile(authentication.getName());
        if(user != null){
            userService.updatePassword(updatePassword, user);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(true, "Password Updated", user));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new SimpleResponse(false, "No User Profile Found."));
        }
    }
}
