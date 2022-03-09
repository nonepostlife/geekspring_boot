package com.geekbrains.geekspring.services.interfaces;

import com.geekbrains.geekspring.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {
    User findUserByName(String username);
    void save(User user);
}
