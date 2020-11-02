package com.eteration.simplebanking.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PhoneBillPaymentTransaction extends BillPaymentTransaction {

    //private String operatorName;
    private String phoneNumber;

    public PhoneBillPaymentTransaction(double amount) {
        super(amount);
    }

    public PhoneBillPaymentTransaction(String payee, String phoneNumber, double amount) {
        super(amount);
        setPayee(payee);
        setPhoneNumber(phoneNumber);
    }
}
