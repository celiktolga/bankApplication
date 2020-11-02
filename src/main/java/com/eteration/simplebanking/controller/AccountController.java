package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.model.*;
import com.eteration.simplebanking.repository.AccountRepository;
import com.eteration.simplebanking.services.AccountService;
import com.eteration.simplebanking.utils.RestConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

// This class is a place holder you can change the complete implementation

@RestController
@RequestMapping(RestConstants.BASE_PATH + RestConstants.V1)
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/{accountNumber}")//RestConstants.ID
    public ResponseEntity<Account> getAccount(@PathVariable String accountNumber) throws AccountNotFoundException {
        Account account = accountService.findAccount(accountNumber);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PostMapping("/credit/{accountNumber}")
    public ResponseEntity<TransactionStatus> credit(@PathVariable String accountNumber, @RequestBody DepositTransaction transaction) throws AccountNotFoundException, InsufficientBalanceException {
        Account account = accountService.findAccount(accountNumber);
        account.post(transaction);
        accountService.saveAccount(account);

        return new ResponseEntity(new TransactionStatus(transaction.getStatus(), transaction.getApprovalCode()), HttpStatus.OK);
    }

    @PostMapping("/debit/{accountNumber}")
    public ResponseEntity<TransactionStatus> debit(@PathVariable String accountNumber, @RequestBody WithdrawalTransaction transaction) throws AccountNotFoundException, InsufficientBalanceException {
        Account account = accountService.findAccount(accountNumber);
        account.post(transaction);
        accountService.saveAccount(account);

        return new ResponseEntity(new TransactionStatus(transaction.getStatus(), transaction.getApprovalCode()), HttpStatus.OK);
    }

    @PostMapping("/phonePayment/{accountNumber}")
    public ResponseEntity<TransactionStatus> phonePayment(@PathVariable String accountNumber, @RequestBody PhoneBillPaymentTransaction  transaction) throws AccountNotFoundException, InsufficientBalanceException {
        Account account = accountService.findAccount(accountNumber);
        account.post(transaction);
        accountService.saveAccount(account);

        return new ResponseEntity(new TransactionStatus(transaction.getStatus(), transaction.getApprovalCode()), HttpStatus.OK);
    }
}