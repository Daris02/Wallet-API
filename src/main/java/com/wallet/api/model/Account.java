package com.wallet.api.model;

import java.sql.Timestamp;
import java.util.List;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Account {
    private String id;
    private String name;
    private Double balance;
    private Timestamp creationDate;
    private String type;
    private Integer currencyId;
    private List<Transaction> transactionList;
    
    public Account(String name, String type, Integer currencyId) {
        this.name = name;
        this.type = type;
        this.currencyId = currencyId;
    }

    public Account(String id, String name, Double balance, Timestamp creationDate, String type, Integer currencyId) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.creationDate = creationDate;
        this.type = type;
        this.currencyId = currencyId;
    }
}
