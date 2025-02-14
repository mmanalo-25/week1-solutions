package com.banking.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public abstract class Account {
    private final String accountNumber;
    private BigDecimal balance;
    private AccountType accountType;
    protected LocalDateTime lastTransaction;

    public Account(AccountType accountType, String accountNumber, BigDecimal balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountType = accountType;
        this.lastTransaction = LocalDateTime.now();

    }

    public String getAccountNumber() {
        return this.accountNumber;
    }


    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String toString() {
        return "AccountType: " + accountType + "\n" + "Account Number: " + accountNumber + "\n" + "Balance: " + balance;

    }

}
