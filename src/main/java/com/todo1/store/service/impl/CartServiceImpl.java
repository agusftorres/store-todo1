package com.todo1.store.service.impl;

import com.todo1.store.ErrorCode;
import com.todo1.store.entity.Cart;
import com.todo1.store.entity.Item;
import com.todo1.store.exceptions.BusinessException;
import com.todo1.store.repository.CartRepository;
import com.todo1.store.repository.ProductRepository;
import com.todo1.store.repository.UserRepository;
import com.todo1.store.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CartServiceImpl implements CartService {

    @Qualifier("cartRepository")
    @Autowired
    CartRepository cartRepository;

    @Qualifier("productRepository")
    @Autowired
    ProductRepository productRepository;

    @Qualifier("userRepository")
    @Autowired
    UserRepository userRepository;

    @Override
    public Cart addItem(Long idProduct, Integer quantity, Long idCart, Long idUser) {
        Cart cart;

        if(idCart == null){
           cart = Cart.builder()
                   .shoppingCart(new ArrayList<>())
                   .build();
           cart = cartRepository.save(cart);
        } else {
            cart = cartRepository.findById(idCart).get();
        }

        Item item = Item.builder()
                .product(productRepository.getById(idProduct))
                .quantity(quantity)
                .build();

        List<Item> listItems = cart.getShoppingCart();
        listItems.add(item);

        cart.setShoppingCart(listItems);
        cart.setUser(userRepository.findById(idUser).get());

        return cartRepository.save(cart);
    }

    @Override
    public Cart deleteItem(Long idItem, Integer quantity, Long idCart, Long idUser) {

        if(idCart == null){
            log.error("No existe el carrito con id = ", idCart);
            throw new BusinessException(ErrorCode.CART_NOT_EXISTS);
        }
        Cart cart = cartRepository.findById(idCart).get();

        List<Item> listItems = cart.getShoppingCart();

        Optional<Item> item = listItems.stream().filter(i -> i.getIdItem().equals(idItem)).findFirst();

        Integer quantityNew = deleteItemsFromList(item.get().getQuantity(), quantity);

        if(quantityNew == 0){
            listItems.remove(item);
        }else{
            listItems.remove(item);
            Item itemNew = item.get();
            itemNew.setQuantity(quantityNew);
            listItems.add(itemNew);
        }

        cart.setShoppingCart(listItems);

        return cartRepository.save(cart);
    }

    private Integer deleteItemsFromList(Integer quantityOld, Integer quantityNew) {
        if(quantityNew >= quantityOld){
            return 0;
        }
        return quantityOld - quantityNew;
    }
}
