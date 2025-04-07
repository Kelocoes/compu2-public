package com.example.demo1.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo1.model.User;
import com.example.demo1.security.SecurityUser;
import com.example.demo1.service.IUserService;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserService userService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userService.findByUsername(username);
        return user.map(SecurityUser::new) // If there is a value from the Optional, create a new SecurityUser with that value
                .orElseThrow(() -> new UsernameNotFoundException("Username not found" + username));
    }

}
