package com.wallet.api.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wallet.api.model.Currency;
import com.wallet.api.model.CurrencyValue;
import com.wallet.api.repository.CurrencyRepository;
import com.wallet.api.repository.CurrencyValueRepository;

@Service
public class CurrencyService {
    private CurrencyRepository currencyRepo = new CurrencyRepository();
    private CurrencyValueRepository currencyValueRepo = new CurrencyValueRepository();

    public Currency getCurrencyById(Integer id) {
        return currencyRepo.getById(id);
    }

    public List<Currency> getAllCurrencies() {
        return currencyRepo.findAll();
    }

    public Currency saveCurrency(Currency currency) {
        Integer id = getAllCurrencies().size();
        currency.setId(id == 0 ? id = 1 : id + 1);
        return currencyRepo.save(currency);
    }

    public List<Currency> saveAllCurrencies(List<Currency> currencies) {
        List<Currency> currenciesList = new ArrayList<>();
        for (Currency currency : currencies) {
            currenciesList.add(saveCurrency(currency));
        }
        return currenciesList;
    }

    public void removeById(Integer id) {
        currencyRepo.removeById(id);
    }

    public List<CurrencyValue> getAllCurrencyValues() {
        return currencyValueRepo.findAll();
    }

    public CurrencyValue getCurrencyValueById(Integer id) {
        return currencyValueRepo.getById(id);
    }

    public CurrencyValue saveCurrencyValue(CurrencyValue toSave) {
        Integer id = getAllCurrencies().size();
        toSave.setId(id == 0 ? id = 1 : id + 2);
        toSave.setDateEffect(LocalDateTime.now());
        return currencyValueRepo.save(toSave);
    }
    
    public List<CurrencyValue> saveAllCurrenciesValues(List<CurrencyValue> currenciesValues) {
        List<CurrencyValue> currenciesValuesList = new ArrayList<>();
        for (CurrencyValue currencyValue : currenciesValues) {
            currenciesValuesList.add(saveCurrencyValue(currencyValue));
        }
        return currenciesValuesList;
    }
}
