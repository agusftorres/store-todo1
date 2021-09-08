package com.todo1.store.controller;

import com.todo1.store.entity.Product;
import com.todo1.store.exceptions.BusinessException;
import com.todo1.store.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping( path = "/api/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public List<Product> getAllProducts(){
        log.info("Llamando al serivicio para obtener la lista de productos");
        return productService.getAll();
    }

    @GetMapping(path = "/{id}")
    public Product getProductById(@PathVariable Long id){
       log.info("Llamando al servicio para obtener un producto por id: {}", id);
       Product productResult = productService.getById(id);

       log.info("Respuesta del servicio al buscar producto: {}", productResult);
       return productResult;
    }

    @PostMapping
    public Product insertProduct(@RequestBody Product product){
        log.info("Llamando al servicio para insertar un producto");
        Product productResult = productService.insert(product);

        log.info("Respuesta del servicio al insertar producto: {}", productResult);
        return productResult;
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        log.info("Llamando al servicio para actualizar un producto, cuyo id: {}", product.getIdProduct());
        Product productResult = productService.update(product);

        log.info("Respuesta del servicio al actualizar producto: {}", productResult);
        return productResult;
    }

    @DeleteMapping(path = "/{id}")
    public void deleteProduct(@PathVariable Long id){
        log.info("Llamando al servicio para eliminar un producto, cuyo id: {}", id);
        productService.delete(id);
    }

}
