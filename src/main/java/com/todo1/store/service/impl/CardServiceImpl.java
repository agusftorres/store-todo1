package com.todo1.store.service.impl;

import com.todo1.store.ErrorCode;
import com.todo1.store.entity.Card;
import com.todo1.store.repository.CardRepository;
import com.todo1.store.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CardServiceImpl implements CardService {

    @Qualifier("cardRepository")
    @Autowired
    CardRepository repository;

    @Override
    public void insert(Card card) {
        if(repository.existsById(card.getId())){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ErrorCode.EXISTS.getReasonPhrase());
        }
        repository.save(card);
    }

    @Override
    public void delete(Long id) {
        if(!repository.existsById(id)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ErrorCode.NOT_EXISTS.getReasonPhrase());
        }
        repository.deleteById(id);
    }
}
