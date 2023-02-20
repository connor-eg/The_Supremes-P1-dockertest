package com.revature.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class BankAccount {

    @Id
    @SequenceGenerator(name = "bankaccount_sequence", 
    sequenceName = "bankaccount_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, 
    generator = "bankaccount_sequence")

    private float amount;
    private int accID;
    private int userID;

    public BankAccount(float amount, Integer accID, Integer userID) {
        this.amount=amount;
        this.accID=accID;
        this.userID=userID;
    }

    public BankAccount(float amount, int userID) {
        this.amount = amount;
        this.userID = userID;
    }

    public float getAmount() {return amount;}
    public void setAmount(float amount) {this.amount = amount;}

    public int getAccID() {return accID;}
    public void setAccID(int accID) {this.accID = accID;}

    public int getUserID() {return userID;}
    public void setUserID(int userID) {this.userID = userID;}

    @Override
    public String toString() {
        return "BankAccount{" +
                "amount=" + amount +
                ", accID=" + accID +
                ", userID=" + userID +
                '}';
    }
}
