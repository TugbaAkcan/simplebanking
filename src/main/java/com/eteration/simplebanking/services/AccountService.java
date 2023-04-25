package com.eteration.simplebanking.services;


import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.AccountGenerator;
import com.eteration.simplebanking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// This class is a place holder you can change the complete implementation
@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account findAccount(String accountNumber) {
        return accountRepository.findByAccountNumberLike(accountNumber);
    }

    public Account generateAccount(String accountNumber) {
        Account account = AccountGenerator.createAccount();
        account.setAccountNumber(accountNumber);
        return accountRepository.save(account);
    }

    public Account updateAccount(Account account) {
        return accountRepository.save(account);
    }
}
