package com.ea.miushop.service;

import com.ea.miushop.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    void saveUser(User user);
    List<User> getAllUsers();
    User getUser(Long id);

}
