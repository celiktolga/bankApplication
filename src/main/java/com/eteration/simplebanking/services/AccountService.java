package com.eteration.simplebanking.services;


import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import javax.transaction.Transactional;
import java.util.Optional;

// This class is a place holder you can change the complete implementation
@Service
@Transactional
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public Account findAccount(String accountId) throws AccountNotFoundException {
        Optional<Account> account = accountRepository.findById(accountId);

        if (!account.isPresent()) {
            throw new AccountNotFoundException(String.format("Account is not found for identifier %s", accountId));
        }
        return account.get();
    }

    public Account saveAccount(final Account account) {
        return accountRepository.save(account);
    }
}
