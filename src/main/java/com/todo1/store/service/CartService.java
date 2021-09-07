package com.todo1.store.service;

import com.todo1.store.entity.Cart;
import org.springframework.stereotype.Component;

@Component
public interface CartService {
    Cart addItem(Long product, Integer quantity, Long idProduct);
    Cart deleteItem(Long idItem, Integer quantity, Long idCart);
}
