package com.shiva.bookstore.controller;

import com.shiva.bookstore.dto.ApiResponse;
import com.shiva.bookstore.dto.SimpleResponse;
import com.shiva.bookstore.entity.Books;
import com.shiva.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/admin")
public class AdminController {
    @GetMapping
    public ResponseEntity<SimpleResponse> getAllBooks() {
        return ResponseEntity.status(HttpStatus.OK).body(new SimpleResponse(true, "Hello admin!"));
    }
    @Autowired
    private BookService bookService;

    @PostMapping("/books/add")
    public ResponseEntity<?> addBook(@RequestBody Books book){
        Optional<Books> result = bookService.addBook(book);
        if(result.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, "Book Added Successfully", result.get()));
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SimpleResponse(false, "Book Added Failed"));
        }
    }

    @PostMapping("/books/update/stock")
    public ResponseEntity<?> updateStock(@RequestBody Books book){
        Optional<Books> result = bookService.updateStock(book);
        if(result.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(true, "Book Updated Successfully", result.get()));
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SimpleResponse(false, "Book Updated Failed"));
        }
    }
    @GetMapping("/books/search")
    public ResponseEntity<?> searchBook(@RequestParam String title){
        List<Books> books = bookService.findByTitle(title);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(true, "Books Found", books));
    }
}