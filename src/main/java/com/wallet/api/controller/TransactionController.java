package com.wallet.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.api.model.Account;
import com.wallet.api.model.Transaction;
import com.wallet.api.service.TransactionService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping({"", "/"})
    public List<Transaction> getTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public Transaction getById(@PathVariable("id") String id) {
        return transactionService.getTransactionById(id);
    }

    @GetMapping("/account/{accountId}")
    public List<Transaction> getTransactionByAccountId(@PathVariable("accountId") String accountId) {
        return transactionService.getAllTransactionsByAccoundId(accountId);
    }

    @PostMapping({"", "/"})
    public List<Account> saveTransactions(@RequestBody List<Transaction> transactions) {
        return transactionService.saveAllTransactions(transactions);
    }
}
