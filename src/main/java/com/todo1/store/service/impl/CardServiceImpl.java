package com.todo1.store.service.impl;

import com.todo1.store.Encrypt;
import com.todo1.store.ErrorCode;
import com.todo1.store.entity.Card;
import com.todo1.store.exceptions.BusinessException;
import com.todo1.store.repository.CardRepository;
import com.todo1.store.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class CardServiceImpl implements CardService {

    @Qualifier("cardRepository")
    @Autowired
    CardRepository repository;

    @Override
    public Card insert(Card card){
//        if(repository.existsById(card.getIdCard())){
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND, ErrorCode.EXISTS.getReasonPhrase());
//        }
        String cardNumber = Encrypt.encrypt(card.getNumber().getBytes(StandardCharsets.UTF_8));
        String cvv = Encrypt.encrypt(card.getCvv().getBytes(StandardCharsets.UTF_8));
        card.setNumber(cardNumber);
        card.setCvv(cvv);

        Card cardResult = repository.save(card);

        String numberResult = Encrypt.decrypt(card.getNumber().getBytes(StandardCharsets.UTF_8));
        cardResult.setNumber(numberResult.substring(12));
        cardResult.setCvv(Encrypt.decrypt(card.getCvv().getBytes(StandardCharsets.UTF_8)));

        return cardResult;
    }

    @Override
    public void delete(Long id) {
        if(!repository.existsById(id)){
            log.error("No existe el producto con id: {} ", id);
            throw new BusinessException(ErrorCode.CARD_NOT_EXISTS);
        }
        repository.deleteById(id);
    }

    @Override
    public Card get(Long id) {
        Card card = repository.getById(id);
        String cardNumber = Encrypt.decrypt(card.getNumber().getBytes(StandardCharsets.UTF_8));
        card.setNumber(cardNumber.substring(12));
        card.setCvv(Encrypt.decrypt(card.getCvv().getBytes(StandardCharsets.UTF_8)));

        return card;
    }
}
