package com.banking.model;

import com.banking.exception.InsufficientFundsException;

import java.math.BigDecimal;

public class SavingsAccount extends Account implements Transaction {
    final BigDecimal minimumBalance = BigDecimal.valueOf(300);
    double interestRate = .03;

    public SavingsAccount(AccountType accountType, String accountNumber, BigDecimal balance) {
        super(accountType, accountNumber, balance);
    }


    public BigDecimal processMonthlyFees() {
        BigDecimal interest = getBalance().multiply(BigDecimal.valueOf(interestRate));
        setBalance(getBalance().add(interest));
        return interest;
    }

    @Override
    public boolean withdraw(BigDecimal amount) {
        if (getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException(getAccountNumber());
        } else if (getBalance().subtract(amount).compareTo(minimumBalance) >= 0) {
            setBalance(getBalance().subtract(amount));
            System.out.println("New balance: " + getBalance());
            return true;
        } else {
            System.out.println("Cannot withdraw. Minimum balance required: " + minimumBalance);
            return false;
        }
    }
}
