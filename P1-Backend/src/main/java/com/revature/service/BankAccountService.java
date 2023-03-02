package com.revature.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.revature.model.BankAccount;
import com.revature.repository.BankAccountRepository;

@Service
public class BankAccountService{
    BankAccountRepository bankAccountRepository;
    public AuthService authService;

    public BankAccountService(BankAccountRepository bankAccountRepository, AuthService authService) {
        super();
        this.bankAccountRepository = bankAccountRepository;
        this.authService = authService;
    }

    public ResponseEntity<List<BankAccount>> getBankAccountsByUserId(Long userId, String sessionToken) {
        List<BankAccount> accounts;
        if (userId <= 0 || sessionToken == null || sessionToken.isEmpty()) {
            return ResponseEntity.status(400).body(null);
        }
        try {
            if (authService.verifyUser(sessionToken, userId) == false) {
                return ResponseEntity.status(403).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
        
        try {
            accounts = bankAccountRepository.findByUserId(userId);
        } catch (Exception e){
            return ResponseEntity.status(500).body(null);
        }
        return ResponseEntity.status(200).body(accounts);
    }

    public ResponseEntity<String> createNewBankAccount(BankAccount ba, String sessionToken) {
        if (ba.getUserId() <= 0 || sessionToken == null || sessionToken.isEmpty()) {
            return ResponseEntity.status(400).body("Invalid userId or sessionToken");
        }
        try {
            if (authService.verifyUser(sessionToken, ba.getUserId()) == false) {
                return ResponseEntity.status(403).body("Unauthorized access to this account.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Something went wrong.");
        }

        try{
            bankAccountRepository.save(ba);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body("Something went wrong.");
        }

        String actionWord = "";
        BankAccount.AccType accType = ba.getAccType();
        if(accType == BankAccount.AccType.CHECKING) {
            actionWord = "checking";
        } else {
            actionWord = "savings";
        }
        return ResponseEntity.status(200).body("Successfully created your " + actionWord + " account");
    }
}