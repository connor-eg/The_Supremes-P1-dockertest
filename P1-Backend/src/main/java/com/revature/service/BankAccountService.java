package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.revature.model.BankAccount;
import com.revature.repository.BankAccountRepository;

@Service
public class BankAccountService{
    BankAccountRepository bankAccountRepository;

    @Autowired
    public BankAccountService(BankAccountRepository bankAccountRepository) {
        super();
        this.bankAccountRepository = bankAccountRepository;
    }

    public ResponseEntity<List<BankAccount>> getBankAccountsByUserId(Long userId) {
        List<BankAccount> accounts;
        try {
            accounts = bankAccountRepository.getBankAccountsByUserId(userId);
        } catch (Exception e){
            return ResponseEntity.status(500).body(null);
        }
        if(accounts.isEmpty()) return ResponseEntity.status(404).body(accounts);
        return ResponseEntity.status(200).body(accounts);
    }

    public ResponseEntity<String> createNewBankAccount(BankAccount ba) {
        try{
            bankAccountRepository.save(ba);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body("Something went wrong.");
        }

        String actionWord = "";
        BankAccount.AccType accType = ba.getAcctype();
        if(accType == BankAccount.AccType.CHECKING) {
            actionWord = "checking";
        } else {
            actionWord = "savings";
        }
        return ResponseEntity.status(200).body("Successfully created your " + actionWord + " account");
    }
}