package com.revature.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.BankAccount;
import com.revature.service.BankAccountService;

@CrossOrigin
@RestController
@RequestMapping("api/v1/bankaccount")
public class BankAccountController {

    private BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        super();
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("my")
    public ResponseEntity<List<BankAccount>> getBankAccountsByUserId(@RequestHeader Long userId, @RequestHeader String sessionToken){
        return bankAccountService.getBankAccountsByUserId(userId, sessionToken);
    }

    @PostMapping
    public ResponseEntity<String> createNewBankAccount(@RequestBody BankAccount ba, @RequestHeader String sessionToken){
        return bankAccountService.createNewBankAccount(ba, sessionToken);
    }
}
