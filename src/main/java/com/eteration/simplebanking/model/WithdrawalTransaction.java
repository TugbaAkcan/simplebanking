package com.eteration.simplebanking.model;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

// This class is a place holder you can change the complete implementation
@Entity
@DiscriminatorValue("WithdrawalTransaction")
public class WithdrawalTransaction extends Transaction {
    public WithdrawalTransaction(Double amount) {
        super(amount, WithdrawalTransaction.class.getSimpleName());
    }

    public WithdrawalTransaction(int amount) {
        super(amount);
    }

    public WithdrawalTransaction() {
    }
}


