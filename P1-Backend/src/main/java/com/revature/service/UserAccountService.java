package com.revature.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.revature.model.UserAccount;
import com.revature.repository.UserAccountRepository;



@Service
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    public UserAccountService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    public ArrayList<UserAccount> getUserAccounts() {
        List<UserAccount> userAccountList = userAccountRepository.findAll();
        ArrayList<UserAccount> accountArrList = new ArrayList<>(userAccountList);
        return accountArrList;
    }

    public void register(UserAccount userAccount) {

        Optional<UserAccount> userAccountOptional = userAccountRepository.findByUsername(userAccount.getUsername());

        if (userAccountOptional.isPresent()) {
            throw new IllegalStateException("Username is already taken.");
        }

        userAccountRepository.save(userAccount);
    }

    public boolean login(String username, String password) {
        Optional<UserAccount> userAccountOptional = userAccountRepository.findByUsernameAndPassword(username, password);
        System.out.println("HEYO LOOK AT THIS " + userAccountOptional);
        if (userAccountOptional.isPresent()) {
            System.out.println("This should contain fields: " + userAccountOptional);
            System.out.println("User: " + userAccountOptional.get().getUsername() + " has logged in successfully!");
        } else {
            System.out.println("This should not contain any fields: " + userAccountOptional);
            System.out.println("Credentials supplied were not correct.");
        }
        return userAccountOptional.isPresent();
    }
}


