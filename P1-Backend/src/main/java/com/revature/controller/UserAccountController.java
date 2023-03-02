package com.revature.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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

    @PostMapping("/register")
    public ResponseEntity<UserAccount> registerNewUserAccount(@RequestBody UserAccount userAccount) {
      return userAccountService.register(userAccount);
    }

    @PostMapping("/login")
    public ResponseEntity<UserAccount> login(@RequestBody UserAccount userAccount) throws Exception {
      return userAccountService.login(userAccount.getUsername(), userAccount.getPassword());
    }

    @PutMapping("/update")
    public ResponseEntity<UserAccount> updateUserAccount(@RequestBody ObjectNode updateForm,
        @RequestHeader String sessionToken) {
      return userAccountService.update(updateForm, sessionToken);
    }
    
    @GetMapping("/getUserIdFromSessionToken")
    public ResponseEntity<UserAccount> getUser(@RequestHeader String sessionToken) {
      return userAccountService.getUserInfo(sessionToken);
    }
    
}
