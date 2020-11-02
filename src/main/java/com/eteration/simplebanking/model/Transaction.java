package com.eteration.simplebanking.model;


// This class is a place holder you can change the complete implementation

import com.eteration.simplebanking.controller.TransactionStatus;
import com.eteration.simplebanking.model.InsufficientBalanceException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@Entity
public abstract class Transaction extends TransactionStatus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date date;
    private double amount;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Transaction(double amount) {
        this.amount = amount;
        this.date = new Date();
    }


    public abstract void makeTransaction(Account account) throws InsufficientBalanceException;

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", date=" + date +
                ", amount=" + amount +
                '}';
    }
}
