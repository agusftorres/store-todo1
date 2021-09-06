package com.todo1.store.service;

import com.todo1.store.entity.Cart;
import com.todo1.store.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CartService {
    List<Product> addItem(Long idProduct);
    List<Product> deleteItem(Long idProduct);
}
