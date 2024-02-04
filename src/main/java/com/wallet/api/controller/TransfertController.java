package com.wallet.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.api.model.Transfert;
import com.wallet.api.service.TransfertService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/transfert")
public class TransfertController {
    private final TransfertService transfertService;

    @GetMapping({"", "/"})
    public List<Transfert> getTransferts() {
        return transfertService.getAllTransferts();
    }

    @PostMapping({"", "/{currencyValue}"})
    public Transfert getTransfertById(
        @RequestBody Transfert transfert,
        @PathVariable("currencyValue") String currencyValue) {
        return transfertService.saveTransfert(
            transfert.getDebtorId(),
            transfert.getCreditorId(),
            transfert.getAmount(), currencyValue);
    }
}
