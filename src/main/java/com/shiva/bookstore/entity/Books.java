package com.shiva.bookstore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(unique = true, nullable = false, length = 13)
    private String isbn;

    @Column(nullable = false)
    private BigDecimal price;

    private String publisher;

    private LocalDate publishedDate;

    private Integer stockQuantity;

    /**
     * Simple validation method for the book.
     */
    public boolean isValid() {
        if (title == null || title.isBlank()) return false;
        if (author == null || author.isBlank()) return false;
        if (isbn == null || isbn.length() != 13) return false;
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) return false;
        if (stockQuantity != null && stockQuantity < 0) return false;
        return true;
    }
}
