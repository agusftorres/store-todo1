package com.todo1.store.service;

import com.todo1.store.entity.Card;
import org.springframework.stereotype.Component;

@Component
public interface CardService {
    void insert(Card card);
    void delete(Long id);
}
