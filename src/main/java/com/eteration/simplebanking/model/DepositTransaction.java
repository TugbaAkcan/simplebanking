package com.eteration.simplebanking.model;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

// This class is a place holder you can change the complete implementation
@Entity
@DiscriminatorValue("DepositTransaction")
public class DepositTransaction extends Transaction {

    public DepositTransaction() {
        super();
    }

    public DepositTransaction(Double amount) {
        super(amount, DepositTransaction.class.getSimpleName());
    }

    public DepositTransaction(int amount) {
        super(amount);
    }
}
