package com.todo1.store.service;

import com.todo1.store.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductService {
    List<Product> getAll();
    Product insert(Product product);
    Product update(Product product);
    void delete(Long id);
    Product getById(Long id);
}
