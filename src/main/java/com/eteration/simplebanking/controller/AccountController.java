package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.InsufficientBalanceException;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// This class is a place holder you can change the complete implementation
@RestController
@RequestMapping(path = "/account/v1")
public class AccountController {

    @Autowired
    private AccountService accountService;


    @GetMapping(value = "/{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable("accountNumber") String accountNumber) {
        return new ResponseEntity<>(accountService.findAccount(accountNumber), HttpStatus.OK);
    }

    @PostMapping(value = "/credit/{accountNumber}")
    public ResponseEntity<TransactionStatus> credit(@PathVariable("accountNumber") String accountNumber, @RequestBody DepositTransaction depositTransaction) {
        Account account = accountService.findAccount(accountNumber);
        account.credit(depositTransaction.getAmount());
        account.addTransaction(depositTransaction);
        accountService.updateAccount(account);
        return new ResponseEntity<>(new TransactionStatus("OK", depositTransaction.getApprovalCode()), HttpStatus.OK);
    }

    @PostMapping(value = "/debit/{accountNumber}")
    public ResponseEntity<TransactionStatus> debit(@PathVariable("accountNumber") String accountNumber, @RequestBody WithdrawalTransaction withdrawalTransaction) throws InsufficientBalanceException {
        Account account = accountService.findAccount(accountNumber);
        account.debit(withdrawalTransaction.getAmount());
        account.addTransaction(withdrawalTransaction);
        accountService.updateAccount(account);
        return new ResponseEntity<>(new TransactionStatus("OK", withdrawalTransaction.getApprovalCode()), HttpStatus.OK);
    }

    @PostMapping(value = "/generate/{accountNumber}")
    public ResponseEntity<Account> generateAccount(@PathVariable("accountNumber") String accountNumber) {
        return new ResponseEntity<>(accountService.generateAccount(accountNumber), HttpStatus.OK);
    }
}