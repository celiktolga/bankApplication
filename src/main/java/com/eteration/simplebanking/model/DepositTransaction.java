package com.eteration.simplebanking.model;


import com.eteration.simplebanking.model.InsufficientBalanceException;

// This class is a place holder you can change the complete implementation
public class DepositTransaction extends Transaction {


    public DepositTransaction(double amount) {
        super(amount);
    }

    @Override
    public void makeTransaction(Account account) {
        account.deposit(this.getAmount());
    }

}
