package com.todo1.store.controller;

import com.todo1.store.entity.Cart;
import com.todo1.store.entity.User;
import com.todo1.store.service.CartService;
import com.todo1.store.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping( path = "/api/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    CartService cartService;

    @PostMapping("/login")
    public User login(@RequestParam("email") String email, @RequestParam("password") String pwd) {

        String token = getJWTToken(email);
        User user = userService.findByEmail(email);
        Cart cart =  Cart.builder()
                .shoppingCart(new ArrayList<>())
                .build();

        cart = cartService.saveCart(cart);
        user.setCart(cart);
        user.setToken(token);

        return userService.update(user);
    }

    @GetMapping(path = "/{id}")
    public User getUser(@PathVariable Long id){
        return userService.get(id);
    }

    @PostMapping(path = "/signin")
    public User insertUser(@RequestBody User user){
       log.info("Llamando al servicio de creación de usuario con email: {} y nombre: {}", user.getEmail(), user.getName());
       User userResult = userService.insert(user);

       log.info("Repuesta del servicio al crear usuario: {} ", userResult);
       return userResult;
    }

    @PutMapping
    public User updateUser(@RequestBody User user){
        log.info("Llamando al servicio de actualización de usuario con id: {}", user.getIdUser());
        User userResult = userService.update(user);

        log.info("Repuesta del servicio al actualizar usuario: {} ", userResult);
        return userResult;
    }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}
