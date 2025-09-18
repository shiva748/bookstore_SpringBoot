package com.shiva.bookstore.controller;

import com.shiva.bookstore.dto.SimpleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {
    @GetMapping
    public ResponseEntity<?> root() {
        return ResponseEntity.status(HttpStatus.OK).body(new SimpleResponse(true, "Hello there, Our Api is live."));
    }
    
}
