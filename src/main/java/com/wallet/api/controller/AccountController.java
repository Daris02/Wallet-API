package com.wallet.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import com.wallet.api.model.Account;
import com.wallet.api.service.AccountService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    @GetMapping({"", "/"})
    public List<Account> getAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping({"/{id}"})
    public Account getAccount(@PathVariable("id") String id) {
        return accountService.getAccountById(id);
    }

    @GetMapping({"/{id}/spendAmount"})
    public Map<String, Double> getTotalSpendAmounts(@PathVariable("id") String id, @RequestParam(name = "start") String start, @RequestParam(name = "end") String end) {
        return accountService.findAllTotalSpendAmounts(id, start, end);
    }

    @PostMapping({"", "/"})
    public List<Account> saveAccount(@RequestBody List<Account> accounts) {
        return accountService.saveAllAccounts(accounts);
    }
}
