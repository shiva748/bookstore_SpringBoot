package com.shiva.bookstore.entity;

import com.shiva.bookstore.service.Validation;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    public Boolean isValid(){
        if(name == null || name.isEmpty() || email == null || !Validation.isValidEmail(email) || password == null || !Validation.isStrongPassword(password)) {
            return false;
        }
        return true;
    }
}
