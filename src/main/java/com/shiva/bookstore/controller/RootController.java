package com.shiva.bookstore.controller;

import com.shiva.bookstore.dto.ApiResponse;
import com.shiva.bookstore.dto.SimpleResponse;
import com.shiva.bookstore.entity.Books;
import com.shiva.bookstore.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RootController {
    @GetMapping
    public ResponseEntity<?> root(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(new SimpleResponse(true, "Hello there, Our Api is live and your session id is "+request.getSession().getId()));
    }
    @Autowired
    BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<?> getAllBooks(@RequestParam String title){
        List<Books> books = bookService.getBooks(title);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<List<Books>>(true,books.size()+" books found.", books));
    }
    
}
