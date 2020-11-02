package com.eteration.simplebanking.model;



// This class is a place holder you can change the complete implementation
public class WithdrawalTransaction extends Transaction {


    public WithdrawalTransaction(double amount) {
        super(amount);
    }

    @Override
    public void makeTransaction(Account account) throws InsufficientBalanceException {
        account.withdraw(this.getAmount());
    }

}


