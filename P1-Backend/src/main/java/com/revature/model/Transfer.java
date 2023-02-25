package com.revature.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.annotation.Reference;
import org.springframework.lang.NonNull;


@Entity
@Table
public class Transfer {
    @Id
    @SequenceGenerator(
        name = "transfer_sequence",
        sequenceName = "transfer_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "transfer_sequence"
    )
    @Reference(to = BankAccount.class)
    private Long id;
    //Need to make this a foreign key later, for now it is just a number
    private Long accountId;
    @NonNull
    private BigDecimal amount = new BigDecimal(0);
    private boolean isDeposit;
    @Column(insertable = false, updatable = false, columnDefinition = "timestamp default current_timestamp")
    private Timestamp time;
    private String description;

    public Transfer() {
    }

    public Transfer(Long accountId, @NonNull BigDecimal amount, boolean isDeposit, String description) {
        this.accountId = accountId;
        this.amount = amount;
        this.isDeposit = isDeposit;
        this.description = description;
    }

    public Transfer(Long traId, Long accountId, @NonNull BigDecimal amount, boolean isDeposit, Timestamp time, String description) {
        this.id = traId;
        this.accountId = accountId;
        this.amount = amount;
        this.isDeposit = isDeposit;
        this.time = time;
        this.description = description;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return this.accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public boolean isIsDeposit() {
        return this.isDeposit;
    }

    public boolean getIsDeposit() {
        return this.isDeposit;
    }

    public void setIsDeposit(boolean isDeposit) {
        this.isDeposit = isDeposit;
    }

    public Timestamp getTime() {
        return this.time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", accountId='" + getAccountId() + "'" +
            ", amount='" + getAmount() + "'" +
            ", isDeposit='" + isIsDeposit() + "'" +
            ", time='" + getTime() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
    
}
