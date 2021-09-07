package com.todo1.store.controller;

import com.todo1.store.entity.Cart;
import com.todo1.store.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping( path = "/api/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping
    public Cart addItem(@RequestParam Long idProduct,@RequestParam Integer quantity,@RequestParam Long idCart, @RequestParam Long idUser){
        log.info(
                "Se llama al servicio que agrega uno o varios items del mismo tipo al carrito, con id de producto: {}, cantidad: {}, id de carrito: {}",
                idProduct, quantity,idCart
        );
        Cart cartResult = cartService.addItem(idProduct,quantity,idCart, idUser);

        log.info("Resultado del servicio: {}", cartResult);
        return cartResult;
    }

    @DeleteMapping
    public Cart deleteItem(@RequestParam Long idProduct,@RequestParam Integer quantity,@RequestParam Long idCart, @RequestParam Long idUser){
        log.info(
                "Se llama al servicio que elimina uno o varios items del mismo tipo al carrito, con id de producto: {}, cantidad: {}, id de carrito: {}, id de usuario: {}",
                idProduct, quantity,idCart, idUser
        );
        Cart cartResult = cartService.deleteItem(idProduct, quantity,idCart, idUser);

        log.info("Resultado obtenido del servicio: {}", cartResult);
        return cartResult;
    }
}
