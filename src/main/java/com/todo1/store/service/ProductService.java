package com.todo1.store.service;

import com.todo1.store.entity.Product;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
public interface ProductService {
    List<Product> getAll() throws Exception;
    void insert(Product product) throws Exception;
    void update(Product product) throws SQLException;
    void delete(Long id);
    Product getById(Long id);
}
