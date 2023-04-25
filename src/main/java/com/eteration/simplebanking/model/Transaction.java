package com.eteration.simplebanking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

// This class is a place holder you can change the complete implementation
@Entity
@Table(name = "transaction")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type",
        discriminatorType = DiscriminatorType.STRING)
public abstract class Transaction implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "accountId")
    @JsonIgnore
    private Account account;
    private Double amount;
    private Date date;
    private String approvalCode;
    @Column(insertable = false, updatable = false)
    private String type;

    public Transaction(int id, Account account, Double amount, Date date, String approvalCode, String type) {
        this.id = id;
        this.account = account;
        this.amount = amount;
        this.date = date;
        this.approvalCode = approvalCode;
        this.type = type;
    }

    public Transaction(Double amount, String type) {
        this.amount = amount;
        this.date = new Date();
        this.type = type;
    }

    public Transaction(int amount) {
        this.amount = (double) amount;
        this.date = new Date();
    }

    public Transaction() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
