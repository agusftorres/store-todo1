package com.todo1.store.service.impl;

import com.todo1.store.ErrorCode;
import com.todo1.store.entity.Product;
import com.todo1.store.exceptions.BusinessException;
import com.todo1.store.repository.ProductRepository;
import com.todo1.store.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Qualifier("productRepository")
    @Autowired
    ProductRepository repository;

    @Override
    public List<Product> getAll() throws Exception {
        try{
            return repository.findAll();
        }catch (Exception e){
            log.info("Error buscando los productos");
            throw new BusinessException(ErrorCode.INTERNAL_ERROR);
        }
    }

    @Override
    public Product insert(Product product){
        repository.save(product);
        return product;
    }

    @Override
    public Product update(Product product) {
        if(!repository.existsById(product.getIdProduct())){
            log.error("No existe el producto con id: {} ", product.getIdProduct());
            throw new BusinessException(ErrorCode.PRODUCT_NOT_EXISTS);
        }
        repository.save(product);
        return product;
    }

    @Override
    public void delete(Long id) {
        if(!repository.existsById(id)){
            log.error("No existe el producto con id: {} ",id);
            throw new BusinessException(ErrorCode.PRODUCT_NOT_EXISTS);
        }
        repository.deleteById(id);
    }

    @Override
    public Product getById(Long id) {
        if(!repository.existsById(id)){
            log.error("No existe el producto con id: {} ",id);
            throw new BusinessException(ErrorCode.PRODUCT_NOT_EXISTS);
        }
        return repository.getById(id);
    }
}
