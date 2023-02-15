package com.revature.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.UserAccount;
import com.revature.service.UserAccountService;

@RestController
@RequestMapping(path = "api/v1/useraccount")
public class UserAccountController {

    private final UserAccountService userAccountService;

    @Autowired
    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping
    public ArrayList<UserAccount> getUsersAccounts() {
        return userAccountService.getUserAccounts();
    }
    
}
