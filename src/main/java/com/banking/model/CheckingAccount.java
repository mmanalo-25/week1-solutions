package com.banking.model;

import com.banking.service.TransactionService;

import java.math.BigDecimal;

public class CheckingAccount extends Account implements Transaction {
    private final BigDecimal monthlyFee = BigDecimal.valueOf(12);
    private final BigDecimal withdrawalLimit = BigDecimal.valueOf(3000);
    private final int transactionLimit = 5;

    public CheckingAccount(AccountType accountType, String accountNumber, BigDecimal balance) {

        super(accountType, accountNumber, balance);
    }

    @Override
    public boolean withdraw(BigDecimal amount) {
        int transactionCount = TransactionService.countTransactionsByDate(getAccountNumber());
        if (transactionCount < transactionLimit) {
            if (getBalance().subtract(amount).compareTo(BigDecimal.ZERO) < 0) {
                System.out.println("Cannot withdraw. Insufficient balance: " + getBalance());
                return false;
            } else if (amount.compareTo(withdrawalLimit) > 0) {
                System.out.println("Cannot withdraw. Exceeds withdrawal limit: " + withdrawalLimit);
                return false;
            } else {
                setBalance(getBalance().subtract(amount));
                System.out.println("Withdrawal successful! New balance: " + getBalance());
                return true;
            }

        } else {
            System.out.println("Exceeds Transaction Limit.");
            return false;
        }


    }

    @Override
    public BigDecimal processMonthlyFees() {
        setBalance(getBalance().subtract(monthlyFee));
        return monthlyFee;
    }

}
