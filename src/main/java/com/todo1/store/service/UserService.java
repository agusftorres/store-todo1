package com.todo1.store.service;

import com.todo1.store.entity.User;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    User get(Long id);
    User insert(User user);
    User update(User user);
}
