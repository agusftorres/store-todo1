package com.todo1.store.controller;

import com.todo1.store.entity.User;
import com.todo1.store.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping( path = "/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public User getUser(Long id){
        return userService.get(id);
    }

    @PostMapping
    public void insertUser(@RequestBody User user){
        userService.insert(user);
    }

    @PutMapping
    public void updateUser(@RequestBody User user){
        userService.update(user);
    }
}
