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
    private Long traid;
    //Need to make this a foreign key later, for now it is just a number
    private Long traaccountid;
    @NonNull
    private BigDecimal traamount = new BigDecimal(0);
    private boolean traisdeposit;
    @Column(insertable = false, updatable = false, columnDefinition = "timestamp default current_timestamp")
    private Timestamp tratime;
    private String tradescription;

    public Transfer() {
    }

    public Transfer(Long traaccountid, @NonNull BigDecimal traamount, boolean traisdeposit, String tradescription) {
        this.traaccountid = traaccountid;
        this.traamount = traamount;
        this.traisdeposit = traisdeposit;
        this.tradescription = tradescription;
    }

    public Transfer(Long traid, Long traaccountid, @NonNull BigDecimal traamount, boolean traisdeposit, Timestamp tratime, String tradescription) {
        this.traid = traid;
        this.traaccountid = traaccountid;
        this.traamount = traamount;
        this.traisdeposit = traisdeposit;
        this.tratime = tratime;
        this.tradescription = tradescription;
    }

    public Long gettraid() {
        return this.traid;
    }

    public void settraid(Long traid) {
        this.traid = traid;
    }

    public Long gettraaccountid() {
        return this.traaccountid;
    }

    public void settraaccountid(Long traaccountid) {
        this.traaccountid = traaccountid;
    }

    public BigDecimal gettraamount() {
        return this.traamount;
    }

    public void settraamount(@NonNull BigDecimal traamount) {
        this.traamount = traamount;
    }

    public boolean gettraisdeposit() {
        return this.traisdeposit;
    }

    public void settraisdeposit(boolean traisdeposit) {
        this.traisdeposit = traisdeposit;
    }

    public Timestamp gettratime() {
        return this.tratime;
    }

    public void settratime(Timestamp tratime) {
        this.tratime = tratime;
    }

    public String gettradescription() {
        return this.tradescription;
    }

    public void settradescription(String tradescription) {
        this.tradescription = tradescription;
    }
    
    @Override
    public String toString() {
        return "{" +
            " traid='" + gettraid() + "'" +
            ", traaccountid='" + gettraaccountid() + "'" +
            ", traamount='" + gettraamount() + "'" +
            ", traisdeposit='" + gettraisdeposit() + "'" +
            ", tratime='" + gettratime() + "'" +
            ", tradescription='" + gettradescription() + "'" +
            "}";
    }
}
