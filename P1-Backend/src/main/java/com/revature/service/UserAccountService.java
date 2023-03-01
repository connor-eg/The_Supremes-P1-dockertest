package com.revature.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;
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

    public ResponseEntity<UserAccount> register(UserAccount userAccount) {

        Optional<UserAccount> userAccountOptional;
        UserAccount newUser;

        try {
            userAccountOptional = userAccountRepository.findByUsername(userAccount.getUsername());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
        
        if (userAccountOptional.isPresent()) {
            return ResponseEntity.status(404).body(null);
        }

        try {
            newUser = userAccountRepository.save(userAccount);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }

        return ResponseEntity.status(200).body(newUser);
    }

    public ResponseEntity<UserAccount> login(String username, String password) {
        Optional<UserAccount> optionalUserAccount;
        UserAccount userAccount;
        try {
            optionalUserAccount = userAccountRepository.findByUsernameAndPassword(username, password);
        } catch (Exception e){
            return ResponseEntity.status(500).body(null);
        }
        if (!optionalUserAccount.isPresent()) {
            return ResponseEntity.status(404).body(null);
        }
        userAccount = optionalUserAccount.get();
        String newToken = userAccount.generateToken();
        userAccount.setSessionToken(newToken);
        try {
            userAccountRepository.save(userAccount);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(userAccount);
        }

        return ResponseEntity.status(200).body(userAccount);

    }

    public ResponseEntity<UserAccount> update(ObjectNode updateForm) {
        Optional<UserAccount> optionalUserAccount;
        UserAccount updateUser;

        try {
            optionalUserAccount = userAccountRepository.findByUsernameAndPassword(
                    updateForm.get("currUsername").asText(), updateForm.get("currPassword").asText());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }

        if (optionalUserAccount.isPresent()) {
            updateUser = optionalUserAccount.get();

            Iterator<String> jsonFields = updateForm.fieldNames();

            while (jsonFields.hasNext()) {
                String jsonFieldName = jsonFields.next();
                String s = updateForm.get(jsonFieldName).asText();

                if (s.isBlank()) {
                    continue;
                } else {
                    switch (jsonFieldName) {
                        case "newUsername":
                            updateUser.setUsername(s);
                            break;
                        case "newPassword":
                            updateUser.setPassword(s);
                            break;
                        case "newEmail":
                            updateUser.setEmail(s);
                            break;
                        case "newFirstName":
                            updateUser.setFirstName(s);
                            break;
                        case "newLastName":
                            updateUser.setLastName(s);
                            break;
                        case "newPhoneNumber":
                            updateUser.setPhoneNumber(s);
                            break;
                        default:
                            break;
                    }
                }
            }
            try {
                userAccountRepository.save(updateUser);
            } catch (Exception e) {
                return ResponseEntity.status(500).body(null);
            }
        } else {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.status(200).body(updateUser);
    }
    
    public boolean checkToken(ObjectNode authForm) {
        try {
             Optional<UserAccount> user = userAccountRepository.findByUsernameAndSessionToken(
                     authForm.get("username").asText(), authForm.get("token").asText());
             if (user.isPresent()) {
                 return true;
             }
         } catch (Exception e) {
         }
         return false;
    }
}


