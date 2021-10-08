package com.todo1.store.service.impl;

import com.todo1.store.entity.Card;
import com.todo1.store.repository.CardRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CardServiceImplTest {

    @Mock
    CardRepository cardRepository;

    @Test
    @DisplayName("Cuando intento intento agregar una tarjeta valida, entonces espero que me devuelva la tarjeta con id")
    void insertTestOk() {
        Card cardExpected = Card.builder()
                .idCard(1L).cvv("223")
                .brand("Visa")
                .goodThru(LocalDateTime.now())
                .idCredit(false)
                .number("3635")
                .build();
        Card cardToInsert = Card.builder()
                .idCard(1L).cvv("223")
                .brand("Visa")
                .goodThru(LocalDateTime.now())
                .idCredit(false)
                .number("1012131425233635")
                .build();

        when(cardRepository.save(any(Card.class))).thenReturn(cardExpected);
        assertEquals(cardExpected, cardToInsert);
    }

    @Test
    void delete() {
    }
}