package com.revature.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.revature.model.UserAccount;
import com.revature.service.UserAccountService;

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
    public ResponseEntity<Boolean> registerNewUserAccount(@RequestBody UserAccount userAccount) {

      boolean result = userAccountService.register(userAccount);

      if (result) {
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
      }
      
      return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login")
    public void login(@RequestBody ObjectNode loginForm) throws Exception {
      userAccountService.login(loginForm.get("username").asText(), loginForm.get("password").asText());
    }

    // Have ResponseEntity return string("Update successful") or ("Update failed: " + reasonForFailure)
    @PutMapping("/update")
    public ResponseEntity<Boolean> updateUserAccount(@RequestBody ObjectNode updateForm) {
      boolean result = userAccountService.update(updateForm);
      if (result) {
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
      }

      return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }
    
}
