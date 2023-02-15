package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.UserAccount;
import com.revature.repository.UserAccountRepository;



@Service
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;
    
    @Autowired
    public UserAccountService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    public ArrayList<UserAccount> getUserAccounts() {
        List<UserAccount> userAccountList = userAccountRepository.findAll();
        ArrayList<UserAccount> accountArrList = new ArrayList<>(userAccountList);
        return accountArrList;
    }
}
