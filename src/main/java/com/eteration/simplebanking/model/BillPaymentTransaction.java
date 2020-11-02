package com.eteration.simplebanking.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BillPaymentTransaction extends WithdrawalTransaction {

    public String payee;

    public BillPaymentTransaction(double amount) {
        super(amount);
    }


}
