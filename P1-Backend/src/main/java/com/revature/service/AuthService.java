package com.revature.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.revature.model.BankAccount;
import com.revature.model.UserAccount;
import com.revature.repository.BankAccountRepository;
import com.revature.repository.TransferRepository;
import com.revature.repository.UserAccountRepository;


@Service
public class AuthService {

    public final UserAccountRepository userAccountRepository;
    public final BankAccountRepository bankAccountRepository;
    public final TransferRepository transferRepository;

    public AuthService(UserAccountRepository userAccountRepository, BankAccountRepository bankAccountRepository, TransferRepository transferRepository) {
        this.userAccountRepository = userAccountRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.transferRepository = transferRepository;
    }
    
    public Boolean verifyTransfer(String sessionToken, Long accountId) {
        Optional<BankAccount> bankAccount;
        Optional<UserAccount> userAccount;

        if (sessionToken != null && accountId != null) {
            try {
                bankAccount = bankAccountRepository.findById(accountId);
            } catch (Exception e) {
                return false;
            }

            if (bankAccount.isPresent()) {
                try {
                    userAccount = userAccountRepository.findByUserAccountIdAndSessionToken(accountId, sessionToken);
                } catch (Exception e) {
                    return false;
                }

                if (userAccount.isPresent()) {
                    return true;
                }
            }
        }
        return false;
    }
      
    public Boolean verifyUser(String sessionToken, Long userAccountId) {
        Optional<UserAccount> userAccount;

        if (sessionToken != null && userAccountId != null) {
            try {
                userAccount = userAccountRepository.findByUserAccountIdAndSessionToken(userAccountId, sessionToken);
            } catch (Exception e) {
                return false;
            }

            if (userAccount.isPresent()) {
                return true;
            }
        }
        return false;
    }
      
    public Boolean verifyBankAccount(String sessionToken, Long userId) {
        Optional<UserAccount> userAccount;

        if (sessionToken != null && userId != null) {
            try {
                userAccount = userAccountRepository.findByUserAccountIdAndSessionToken(userId, sessionToken);
            } catch (Exception e) {
                return false;
            }

            if (userAccount.isPresent()) {
                return true;
            }
        }
        return false;
    }
}
