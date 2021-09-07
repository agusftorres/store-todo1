package com.todo1.store.service.impl;

import com.todo1.store.Encrypt;
import com.todo1.store.ErrorCode;
import com.todo1.store.entity.User;
import com.todo1.store.exceptions.BusinessException;
import com.todo1.store.repository.UserRepository;
import com.todo1.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;

@Service
public class UserServiceImpl implements UserService {

    @Qualifier("userRepository")
    @Autowired
    UserRepository repository;

    @Override
    public User get(Long id) {
        return repository.getById(id);
    }

    @Override
    public User insert(User user) {
//        if(repository.existsById(user.getIdUser())){
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND, ErrorCode.EXISTS.getReasonPhrase());
//        }
        //TODO findByEmail

        String password = Encrypt.encrypt(user.getPassword().getBytes(StandardCharsets.UTF_8));
        user.setPassword(password);
        return repository.save(user);
    }

    @Override
    public User update(User user) {
        if(!repository.existsById(user.getIdUser())){
            throw new BusinessException(ErrorCode.USER_NOT_EXISTS);
        }
        return repository.save(user);
    }
}
