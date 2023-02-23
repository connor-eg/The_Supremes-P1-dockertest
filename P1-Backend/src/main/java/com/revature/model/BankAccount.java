package com.revature.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class BankAccount {
    public enum AccType {CHECKING,SAVING}
    @Id
    @SequenceGenerator(name = "bankaccount_sequence", 
    sequenceName = "bankaccount_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, 
    generator = "bankaccount_sequence")
    private long accID;
    private long userID;
    private AccType accType;
    private BigDecimal balance;

    public BankAccount(AccType accType, BigDecimal balance, Long accID, Long userID) {
        this.accType=accType;
        this.balance=balance;
        this.accID=accID;
        this.userID=userID;
    }

    public BankAccount(AccType accType, BigDecimal balance, Long userID) {
        this.accType=accType;
        this.balance = balance;
        this.userID = userID;
    }

    public BankAccount() {}

    public AccType getAccType() {return accType;}
    public void setAccType(AccType accType) {this.accType = accType;}

    public BigDecimal getAmount() {return balance;}
    public void setAmount(BigDecimal balance) {this.balance = balance;}

    public long getAccID() {return accID;}
    public void setAccID(int accID) {this.accID = accID;}

    public long getUserID() {return userID;}
    public void setUserID(int userID) {this.userID = userID;}

    @Override
    public String toString() {
        return "BankAccount{" +
                "balance= " + balance +                
                ", accType=" + accType +
                ", accID= " + accID +
                ", userID= " + userID +
                '}';
    }
}
