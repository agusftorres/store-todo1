package com.todo1.store.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long idTransaction;
    private LocalDateTime createdAt;
    private Long voucher;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Item> purchasedProducts;

    private BigDecimal amount;
    private String state;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String payMethod;
    private Boolean isSell;

}
