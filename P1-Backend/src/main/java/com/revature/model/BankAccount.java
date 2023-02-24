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
    private long bacid;
    //TODO: Make this a reference to the user accounts table
    private long bacuserid;
    private AccType acctype;
    @Column(insertable = false, updatable = false, columnDefinition = "numeric(19,2) default 0")
    private BigDecimal balance;

    public BankAccount(AccType accType, BigDecimal balance, Long bacid, Long bacuserid) {
        this.acctype=accType;
        this.balance=balance;
        this.bacid=bacid;
        this.bacuserid=bacuserid;
    }

    public BankAccount(AccType accType, Long bacuserid) {
        this.acctype = accType;
        this.bacuserid = bacuserid;
    }

    public BankAccount() {}

    public AccType getAcctype() {return acctype;}
    public void setAcctype(AccType accType) {this.acctype = accType;}

    public BigDecimal getBalance() {return balance;}
    public void setBalance(BigDecimal balance) {
        if (balance != null) {
            this.balance = balance;
        }
    }

    public long getBacId() {return bacid;}
    public void setBacId(int bacid) {this.bacid = bacid;}

    public long getBacUserId() {return bacuserid;}
    public void setBacUserId(int bacuserid) {this.bacuserid = bacuserid;}

    @Override
    public String toString() {
        return "BankAccount{" +
                "balance= " + balance +                
                ", accType=" + acctype +
                ", bacid= " + bacid +
                ", bacuserid= " + bacuserid +
                '}';
    }
}
