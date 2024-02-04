package com.wallet.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.api.model.Currency;
import com.wallet.api.model.CurrencyValue;
import com.wallet.api.service.CurrencyService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/currencies")
public class CurrencyController {
    private final CurrencyService currencyService;

    @GetMapping({"", "/"})
    public List<Currency> getCurrencies() {
        return currencyService.getAllCurrencies();
    }

    @GetMapping({"/{id}"})
    public Currency getCurrency(@PathVariable("id") Integer id) {
        return currencyService.getCurrencyById(id);
    }
    
    @PostMapping({"", "/"})
    public List<Currency> saveCurrencies(@RequestBody List<Currency> currencies) {
        return currencyService.saveAllCurrencies(currencies);
    }

    @GetMapping({"/value"})
    public List<CurrencyValue> getCurrenciesValues() {
        return currencyService.getAllCurrencyValues();
    }

    @GetMapping({"/value/{id}"})
    public CurrencyValue getCurrencyValue(@PathVariable("id") Integer id) {
        return currencyService.getCurrencyValueById(id);
    }
    
    @PostMapping({"/value"})
    public List<CurrencyValue> saveCurrenciesValues(@RequestBody List<CurrencyValue> currenciesValues) {
        return currencyService.saveAllCurrenciesValues(currenciesValues);
    }
}
