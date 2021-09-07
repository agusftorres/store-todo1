package com.todo1.store.controller;

import com.todo1.store.entity.Cart;
import com.todo1.store.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping( path = "/api/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping
    public Cart addItem(Long idProduct, Integer quantity, Long idCart){
        return cartService.addItem(idProduct,quantity,idCart);
    }
}
