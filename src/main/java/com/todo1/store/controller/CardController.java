package com.todo1.store.controller;

import com.todo1.store.entity.Card;
import com.todo1.store.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping( path = "/api/card")
public class CardController {

    @Autowired
    CardService cardService;

    @GetMapping(path = "/{id}")
    public Card getCard(@PathVariable Long id){
        log.info("Se llama al servicio para obtener una tarjeta de id: {}", id);
        Card cardResult = cardService.get(id);

        log.info("Resultado obtenido por el servicio: {}", cardResult);
        return cardResult;
    }

    @PostMapping
    public Card insertCard(@RequestBody Card card){
        log.info("Se llama al servicio para insertar una tarjeta");
        Card cardResult = cardService.insert(card);

        log.info("Resultado obtenido por el servicio: {}", cardResult);
        return cardResult;
    }
}
