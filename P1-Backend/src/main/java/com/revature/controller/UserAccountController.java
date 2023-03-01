package com.revature.controller;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.revature.model.UserAccount;
import com.revature.service.UserAccountService;

@CrossOrigin
@RestController
@RequestMapping(path = "/home")
public class UserAccountController {

    private final UserAccountService userAccountService;

    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping("/users")
    public ArrayList<UserAccount> getUsersAccounts() {
        return userAccountService.getUserAccounts();
    }

    @PostMapping("/register")
    public ResponseEntity<UserAccount> registerNewUserAccount(@RequestBody UserAccount userAccount) {
     return userAccountService.register(userAccount);
    }

    @PostMapping("/login")
    public ResponseEntity<UserAccount> login(@RequestBody ObjectNode loginForm) throws Exception {
      return userAccountService.login(loginForm.get("username").asText(), loginForm.get("password").asText());
    }

    @PutMapping("/update")
    public ResponseEntity<UserAccount> updateUserAccount(@RequestBody ObjectNode updateForm) {
      return userAccountService.update(updateForm);
    }
    
}
