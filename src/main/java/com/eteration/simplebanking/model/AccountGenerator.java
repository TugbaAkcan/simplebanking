package com.eteration.simplebanking.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountGenerator {

    public static Account createAccount() {
        Account account = new Account();
        account.setAccountNumber("669-7788");
        account.setOwner("Kerem Karaca");
        account.setBalance(1000.0);
        account.setCreateDate(new Date());
        account.setAccountId(5);
        List<Transaction> transactionList = new ArrayList<>();
        DepositTransaction depositTransaction = new DepositTransaction(1000.0);
        depositTransaction.setAccount(account);
        depositTransaction.setApprovalCode("c2b64f64-53d6-4d66-9068-46d62d1e6506");
        transactionList.add(depositTransaction);
        account.setTransactions(transactionList);
        return account;
    }
}
