package com.eteration.simplebanking.model;

import javax.persistence.Entity;

@Entity
public class BillPaymentTransaction extends WithdrawalTransaction {
    String payee;

    public BillPaymentTransaction() {
    }

    public BillPaymentTransaction(Double amount, String payee) {

        super(amount);
        this.payee = payee;
    }

    public BillPaymentTransaction(int amount, String payee) {
        super(amount);
        this.payee = payee;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }
}
