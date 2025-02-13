package com.banking.exception;

public class AccountNotFoundException extends BankingException {
    public AccountNotFoundException(String accountNumber) {
        super("Account number: " + accountNumber + " not found.");
    }
}

