package com.revature.model;

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
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private int bankAccountNumber;

    
    public UserAccount() {
    }

    public UserAccount(String firstName, String lastName, int age, String email, int bankAccountNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.bankAccountNumber = bankAccountNumber;
    }

    public UserAccount(long userAccountId, String firstName, String lastName, int age, String email, int bankAccountNumber) {
        this.userAccountId = userAccountId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.bankAccountNumber = bankAccountNumber;
    }

    public long getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(int userAccountId) {
        this.userAccountId = userAccountId;
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
    
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public int getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(int bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    @Override
    public String toString() {
        String s = "UserAccount[userAccountId: " + userAccountId 
                + ", firstName: " + firstName 
                + ", lastName: " + lastName 
                + ", age: " + age 
                + ", email: " + email 
                + ", bankAccountNumber: " + bankAccountNumber + "]";
        return s;
    }
    
}
