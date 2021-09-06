package com.todo1.store.service.impl;

import com.todo1.store.entity.Product;
import com.todo1.store.repository.CartRepository;
import com.todo1.store.repository.ProductRepository;
import com.todo1.store.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Qualifier("cartRepository")
    @Autowired
    CartRepository cartRepository;

    @Qualifier("productRepository")
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> addItem(Long idProduct) {
        Optional<Product> product = productRepository.findById(idProduct);

        return null;
    }

    @Override
    public List<Product> deleteItem(Long idProduct) {
        return null;
    }
}
