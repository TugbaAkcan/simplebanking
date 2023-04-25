package com.eteration.simplebanking.model;


// This class is a place holder you can change the complete implementation

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "account")
public class Account implements Serializable {

    @Id
    @GeneratedValue
    private Integer accountId;
    private String owner;
    private String accountNumber;
    private double balance;
    private Date createDate;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaction> transactions;

    public void debit(Double amount) throws InsufficientBalanceException {
        if (balance < amount) {
            throw new InsufficientBalanceException();
        }
        balance = balance - amount;
    }

    public void credit(Double amount) {
        balance = balance + amount;
    }

    public void addTransaction(Transaction transaction) {
        transaction.setApprovalCode(UUID.randomUUID().toString());
        transaction.setAccount(this);
        transaction.setDate(new Date());
        if (transactions == null) {
            transactions = new ArrayList<>();
            transactions.add(transaction);
        } else {
            transactions.add(transaction);
        }

    }

    public void post(Transaction transaction) throws InsufficientBalanceException {

        if (transaction instanceof DepositTransaction) {
            credit(transaction.getAmount());
            addTransaction(transaction);
        } else if (transaction instanceof WithdrawalTransaction) {
            debit(transaction.getAmount());
            addTransaction(transaction);
        }
    }

    public Account(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.transactions = new ArrayList<>();
        this.createDate = new Date();
    }

    public Account() {
    }

    public Account(String owner, String accountNumber, Double balance, Date createDate, List<Transaction> transactions) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.createDate = createDate;
        this.transactions = transactions;
    }

    public void deposit(int i) {
        credit((double) i);
    }

    public void withdraw(int i) throws InsufficientBalanceException {
        debit((double) i);
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

}
