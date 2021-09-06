package com.todo1.store.service;

import com.todo1.store.entity.User;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    void insert(User user);
    void update(User user);
}