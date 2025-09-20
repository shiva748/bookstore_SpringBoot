package com.shiva.bookstore.service;

import com.shiva.bookstore.dto.UpdatePassword;
import com.shiva.bookstore.entity.Users;
import com.shiva.bookstore.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public Users getUserProfile(String email) {
        Optional<Users> user = userRepo.findByEmail(email);
        return user.orElse(null);
    }
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public Boolean updatePassword(UpdatePassword updatePassword, Users user) {
        if(encoder.matches(updatePassword.getOldPassword(), user.getPassword())) {
            user.setPassword(encoder.encode(updatePassword.getNewPassword()
            ));
            userRepo.save(user);
            return true;
        }else{
            return false;
        }
    }
}
