package com.eteration.simplebanking.model;


// This class is a place holder you can change the complete implementation

import com.eteration.simplebanking.controller.TransactionStatus;
import com.eteration.simplebanking.model.InsufficientBalanceException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String owner;
    private String accountNumber;
    private volatile double balance;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();

    public Account(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (this.balance < amount) {
            throw new InsufficientBalanceException();
        }
        setBalance(getBalance() - amount);
    }

    public void deposit(double amount) {
        setBalance(getBalance() + amount);
    }

    public void post(Transaction transaction) throws InsufficientBalanceException {
        try {
            transaction.makeTransaction(this);//
            transaction.setApprovalCode(transaction.getApprovalCode());
            transaction.setStatus(HttpStatus.OK.name());
        } catch (InsufficientBalanceException exp) {
            transaction.setStatus(HttpStatus.NOT_MODIFIED.name());
            throw exp;
        } finally {
            transactions.add(transaction);
        }
    }
}
