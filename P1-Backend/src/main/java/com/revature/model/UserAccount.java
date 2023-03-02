package com.revature.model;

import java.time.LocalDate;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table
public class UserAccount {

    @Id
    @SequenceGenerator(name = "useraccount_sequence", sequenceName = "useraccount_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "useraccount_sequence")
    private long userAccountId;

    private String username;
    private String password;
    private String sessionToken;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String phoneNumber;

    public UserAccount() {
        
    }


    public UserAccount(String username, String password, String firstName, String lastName) 
    {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserAccount(String username, String password, String sessionToken, String firstName,
            String lastName, String middleName, String email, String phoneNumber) 
    {
        this.username = username;
        this.password = password;
        this.sessionToken = sessionToken;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public UserAccount(long userAccountId, String username, String password, String sessionToken, String firstName,
            String lastName, String middleName, String email, String phoneNumber) 
    {
        this.userAccountId = userAccountId;
        this.username = username;
        this.password = password;
        this.sessionToken = sessionToken;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public long getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(int userAccountId) {
        this.userAccountId = userAccountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String generateToken() {
        int low = 48;
        int high = 122;
        int strLen = 32;
        Random random = new Random();

        String newToken = random.ints(low, high + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
            .limit(strLen)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
      
        return newToken;
    }


    @Override
    public String toString() {
        String s = "UserAccount[userAccountId: " + userAccountId
                + ", username: " + username
                + ", password: " + password
                + ", sessionToken " + sessionToken 
                + ", firstName: " + firstName 
                + ", lastName: " + lastName
                + ", middleName: " + middleName 
                + ", email: " + email 
                + ", phoneNumber: " + phoneNumber + "]";
        return s;
    }
    
}
