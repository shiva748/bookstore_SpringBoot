package com.shiva.bookstore.service;

import com.shiva.bookstore.entity.Books;
import com.shiva.bookstore.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;
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

    public List<Books> findByTitle(String title){
        List<Books> books = bookRepo.findAllByTitle(title);
        return books;
    }
    public List<Books> getBooks(String title){
        if(title==null || title.isEmpty()){
            List<Books> books = bookRepo.findAll();
            return books;
        }else{
            List<Books> books = bookRepo.findAllByTitle(title);
            return books;
        }
    }
}
