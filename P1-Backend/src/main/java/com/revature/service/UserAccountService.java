package com.revature.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
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

    public boolean register(UserAccount userAccount) {

        Optional<UserAccount> userAccountOptional = userAccountRepository.findByUsername(userAccount.getUsername());
        if (userAccountOptional.isPresent()) {
            return false;
        }

        UserAccount newUser;
        newUser = userAccountRepository.save(userAccount);
        if (newUser.getUserAccountId() == 0) {
            return false;
        }

        return true;
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

    public boolean update(ObjectNode updateForm) {
        Optional<UserAccount> userAccountOptional = userAccountRepository.findByUsernameAndPassword(
                updateForm.get("currUsername").asText(), updateForm.get("currPassword").asText());
        
        if (userAccountOptional.isPresent()) {
            UserAccount updateUser = new UserAccount();
            updateUser = userAccountOptional.get();

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
            userAccountRepository.save(updateUser);
        } else {
            System.out.println("Current user could not be found.");
        }
        return userAccountOptional.isPresent();
    }
}


