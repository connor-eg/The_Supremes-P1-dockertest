package com.revature.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


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
    private Long traid;
    private Long traaccountid;
    private BigDecimal traamount;
    private boolean traisdeposit;
    private Timestamp tratime;
    private String tradescription;


    public Transfer() {
    }

    public Transfer(Long traAccountId, BigDecimal traAmount, boolean traIsDeposit, Timestamp traTime, String traDescription) {
        this.traaccountid = traAccountId;
        this.traamount = traAmount;
        this.traisdeposit = traIsDeposit;
        this.tratime = traTime;
        this.tradescription = traDescription;
    }

    public Transfer(Long traId, Long traAccountId, BigDecimal traAmount, boolean traIsDeposit, Timestamp traTime, String traDescription) {
        this.traid = traId;
        this.traaccountid = traAccountId;
        this.traamount = traAmount;
        this.traisdeposit = traIsDeposit;
        this.tratime = traTime;
        this.tradescription = traDescription;
    }

    public Long getTraid() {
        return this.traid;
    }

    public void setTraid(Long traId) {
        this.traid = traId;
    }

    public Long getTraaccountid() {
        return this.traaccountid;
    }

    public void setTraaccountid(Long traAccountId) {
        this.traaccountid = traAccountId;
    }

    public BigDecimal getTraamount() {
        return this.traamount;
    }

    public void setTraamount(BigDecimal traAmount) {
        this.traamount = traAmount;
    }

    public boolean isTraisdeposit() {
        return this.traisdeposit;
    }

    public boolean getTraIsDeposit() {
        return this.traisdeposit;
    }

    public void setTraisdeposit(boolean traIsDeposit) {
        this.traisdeposit = traIsDeposit;
    }

    public Timestamp getTratime() {
        return this.tratime;
    }

    public void setTratime(Timestamp traTime) {
        this.tratime = traTime;
    }

    public String getTradescription() {
        return this.tradescription;
    }

    public void setTradescription(String traDescription) {
        this.tradescription = traDescription;
    }
    
    @Override
    public String toString() {
        return "{" +
            " traId='" + getTraid() + "'" +
            ", traAccountId='" + getTraaccountid() + "'" +
            ", traAmount='" + getTraamount() + "'" +
            ", traIsDeposit='" + isTraisdeposit() + "'" +
            ", traTime='" + getTratime() + "'" +
            ", traDescription='" + getTradescription() + "'" +
            "}";
    }
}
