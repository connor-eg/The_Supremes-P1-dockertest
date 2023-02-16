package com.revature.controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.revature.model.UserAccount;
import com.revature.service.UserAccountService;

@RestController
@RequestMapping(path = "/home")
public class UserAccountController {

    private final UserAccountService userAccountService;

    @Autowired
    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping("/users")
    public ArrayList<UserAccount> getUsersAccounts() {
        return userAccountService.getUserAccounts();
    }

    @PostMapping("/register")
    public void registerNewUserAccount(@RequestBody UserAccount userAccount) {
        System.out.println(userAccount);
        userAccountService.register(userAccount);
    }

    @PostMapping("/login")
    public void login(@RequestBody ObjectNode loginForm) throws Exception {
        System.out.println("Username: " + loginForm.get("username") + "\nPassword: " + loginForm.get("password"));
        userAccountService.login(loginForm.get("username").asText(), loginForm.get("password").asText());
    }
    
}
