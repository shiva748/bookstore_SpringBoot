package com.shiva.bookstore.service;

import com.shiva.bookstore.entity.Books;
import com.shiva.bookstore.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepo bookRepo;

    public Optional<Books> addBook(Books book) {
        if(!book.isValid()){
            return Optional.empty();
        }
        return Optional.of(bookRepo.save(book));
    }

    public Optional<Books> updateStock(Books book) {
        Optional<Books> books = bookRepo.findById(book.getId());
        if(books.isPresent()){
            books.get().setStockQuantity(book.getStockQuantity());
            return Optional.of(bookRepo.save(books.get()));
        }else{
            return Optional.empty();
        }
    }
}
