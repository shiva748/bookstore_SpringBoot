package com.shiva.bookstore.controller;

import com.shiva.bookstore.dto.ApiResponse;
import com.shiva.bookstore.dto.SimpleResponse;
import com.shiva.bookstore.entity.Books;
import com.shiva.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/admin")
public class AdminController {
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
}
