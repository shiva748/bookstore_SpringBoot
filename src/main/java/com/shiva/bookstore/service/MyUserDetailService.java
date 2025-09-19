package com.shiva.bookstore.service;

import com.shiva.bookstore.entity.Users;
import com.shiva.bookstore.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> user = userRepo.findByEmail(email);
        if(user.isPresent()){
            return User.withUsername(user.get().getEmail()).password(user.get().getPassword()).roles(user.get().getRole()).build();
        }else{
            throw new UsernameNotFoundException("User not found");
        }
    }
}
