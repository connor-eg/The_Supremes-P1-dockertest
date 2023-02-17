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
    private Long traId;
    //Need to make this a foreign key later, for now it is just a number
    private Long traAccountId;
    @NonNull
    private BigDecimal traAmount = new BigDecimal(0);
    private boolean traIsDeposit;
    @Column(insertable = false, updatable = false, columnDefinition = "timestamp default current_timestamp")
    private Timestamp traTime;
    private String traDescription;


    public Transfer() {
    }

    public Transfer(Long traAccountId, @NonNull BigDecimal traAmount, boolean traIsDeposit, String traDescription) {
        
        this.traAccountId = traAccountId;
        this.traAmount = traAmount;
        this.traIsDeposit = traIsDeposit;
        this.traDescription = traDescription;
    }

    public Transfer(Long traId, Long traAccountId, @NonNull BigDecimal traAmount, boolean traIsDeposit, Timestamp traTime, String traDescription) {
        this.traId = traId;
        this.traAccountId = traAccountId;
        this.traAmount = traAmount;
        this.traIsDeposit = traIsDeposit;
        this.traTime = traTime;
        this.traDescription = traDescription;
    }

    public Long getTraid() {
        return this.traId;
    }

    public void setTraid(Long traId) {
        this.traId = traId;
    }

    public Long gettraAccountId() {
        return this.traAccountId;
    }

    public void settraAccountId(Long traAccountId) {
        this.traAccountId = traAccountId;
    }

    public BigDecimal gettraamount() {
        return this.traAmount;
    }

    public void settraamount(@NonNull BigDecimal traAmount) {
        this.traAmount = traAmount;
    }

    public boolean istraIsDeposit() {
        return this.traIsDeposit;
    }

    public boolean gettraIsDeposit() {
        return this.traIsDeposit;
    }

    public void settraIsDeposit(boolean traIsDeposit) {
        this.traIsDeposit = traIsDeposit;
    }

    public Timestamp gettratime() {
        return this.traTime;
    }

    public void settratime(Timestamp traTime) {
        this.traTime = traTime;
    }

    public String gettradescription() {
        return this.traDescription;
    }

    public void settradescription(String traDescription) {
        this.traDescription = traDescription;
    }
    
    @Override
    public String toString() {
        return "{" +
            " traId='" + getTraid() + "'" +
            ", traAccountId='" + gettraAccountId() + "'" +
            ", traAmount='" + gettraamount() + "'" +
            ", traIsDeposit='" + istraIsDeposit() + "'" +
            ", traTime='" + gettratime() + "'" +
            ", traDescription='" + gettradescription() + "'" +
            "}";
    }
}
