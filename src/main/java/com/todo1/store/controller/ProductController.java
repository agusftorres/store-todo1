package com.todo1.store.controller;

import com.todo1.store.entity.Product;
import com.todo1.store.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping( "/api/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() throws Exception {
        return productService.getAll();
    }

    @GetMapping(path = "/{id}")
    public Product getProductById(@PathVariable Long id){
       return productService.getById(id);
    }

    @PostMapping
    public void insertProduct(@RequestBody Product product) throws Exception {
        productService.insert(product);
    }

    @PutMapping
    public void updateProduct(@RequestBody Product product) throws SQLException {
        productService.update(product);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.delete(id);
    }

}
