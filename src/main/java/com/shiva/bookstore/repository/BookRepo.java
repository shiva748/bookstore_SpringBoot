package com.shiva.bookstore.repository;

import com.shiva.bookstore.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepo extends JpaRepository<Books, Long> {
    List<Books> findAllByTitle(String title);
}
