package com.todo1.store.service.impl;

import com.todo1.store.ErrorCode;
import com.todo1.store.entity.User;
import com.todo1.store.repository.UserRepository;
import com.todo1.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {

    @Qualifier("userRepository")
    @Autowired
    UserRepository repository;

    @Override
    public void insert(User user) {
        if(repository.existsById(user.getId())){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ErrorCode.EXISTS.getReasonPhrase());
        }
        repository.save(user);
    }

    @Override
    public void update(User user) {
        if(!repository.existsById(user.getId())){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ErrorCode.NOT_EXISTS.getReasonPhrase());
        }
        repository.save(user);
    }
}
