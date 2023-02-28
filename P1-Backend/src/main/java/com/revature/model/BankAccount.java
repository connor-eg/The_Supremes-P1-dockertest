package com.revature.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class BankAccount {
    public enum AccType {CHECKING, SAVINGS}
    @Id
    @SequenceGenerator(name = "bankaccount_sequence", 
    sequenceName = "bankaccount_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, 
    generator = "bankaccount_sequence")
    private long id;
    //TODO: Make this a reference to the user accounts table
    private long userId;
    private AccType accType;
    @Column(insertable = false, updatable = false, columnDefinition = "numeric(19,2) default 0")
    private BigDecimal balance;


    public BankAccount(long id, long userId, AccType accType, BigDecimal balance) {
        this.id = id;
        this.userId = userId;
        this.accType = accType;
        this.balance = balance;
    }

    public BankAccount(long userId, AccType accType) {
        this.userId = userId;
        this.accType = accType;
    }

    public BankAccount() {}


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public AccType getAccType() {
        return this.accType;
    }

    public void setAccType(AccType accType) {
        this.accType = accType;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        if (balance != null) {
            this.balance = balance;
        }
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", userId='" + getUserId() + "'" +
            ", accType='" + getAccType() + "'" +
            ", balance='" + getBalance() + "'" +
            "}";
    }
    
}
