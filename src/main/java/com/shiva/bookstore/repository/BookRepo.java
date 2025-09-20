package com.shiva.bookstore.repository;

import com.shiva.bookstore.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepo extends JpaRepository<Books, Long> {

    @Query("SELECT b FROM Books b WHERE b.title LIKE %:title% OR b.author Like %:title%")
    List<Books> findAllByTitle(String title);
}
