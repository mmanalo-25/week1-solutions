package com.banking.service;

import com.banking.exception.AccountNotFoundException;
import com.banking.exception.InvalidDepositException;
import com.banking.model.Account;
import com.banking.model.Transaction;
import com.banking.model.TransactionRecord;
import com.banking.util.TransactionUtil;

import java.math.BigDecimal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionService {
    private final AccountService accountService;
    static private final List<TransactionRecord> transactionHistory = new ArrayList<>();
    TransactionRecord transactionRecord;


    public TransactionService(AccountService accountService) {
        this.accountService = accountService;
    }

    public static void updateTransactionHistory(TransactionRecord transactionRecord) {
        transactionHistory.add(transactionRecord);
    }

    public void deposit(String accountNumber, BigDecimal amount) {
        Account account = accountService.getAccount(accountNumber);
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidDepositException();
        }
        BigDecimal balance = account.getBalance().add(amount);
        account.setBalance(balance);

        accountService.updateAccount(accountNumber, account);
        transactionRecord = new TransactionRecord(account.getAccountNumber(), "DEPOSIT", amount, account.getBalance(), "SUCCESS");
        transactionHistory.add(transactionRecord);
        TransactionLogger.logTransaction(transactionRecord);
        System.out.println(amount + " successfully deposited to Account Number " + accountNumber);

    }

    public void processMonthlyFees(String accountNumber) {
        Account account = accountService.getAccount(accountNumber);
        if (account instanceof Transaction) {
            Transaction transaction = (Transaction) account;
            BigDecimal interest = transaction.processMonthlyFees();
            accountService.updateAccount(account.getAccountNumber(), account);
            transactionRecord = new TransactionRecord(account.getAccountNumber(), "MONTHLY FEE",interest , account.getBalance(), "SUCCESS");
            transactionHistory.add(transactionRecord);
            TransactionLogger.logTransaction(transactionRecord);
            System.out.println("Interest : " + interest + "have deposited to Account Number " + accountNumber);
        }

    }

    public void withdraw(String accountNumber, BigDecimal amount) {
        Account account = accountService.getAccount(accountNumber);

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidDepositException();
        }
        try {

            if (account instanceof Transaction) {
                Transaction transaction = (Transaction) account;
                boolean success = transaction.withdraw(amount);
                if (success) {

                    accountService.updateAccount(account.getAccountNumber(), account);
                    transactionRecord = new TransactionRecord(account.getAccountNumber(), "WITHDRAWAL", amount, account.getBalance(), "SUCCESS");
                    transactionHistory.add(transactionRecord);
                    TransactionLogger.logTransaction(transactionRecord);
                    System.out.println("Withdrawal successful!");

                } else {
                    accountService.updateAccount(account.getAccountNumber(), account);
                    transactionRecord = new TransactionRecord(account.getAccountNumber(), "WITHDRAWAL", amount, account.getBalance(), "FAILED");
                    transactionHistory.add(transactionRecord);
                    TransactionLogger.logTransaction(transactionRecord);
                    System.out.println("Withdrawal failed.");
                }
            } else {
                System.out.println("Invalid account type for withdrawal.");
            }
        } catch (NullPointerException e) {
            System.out.println("Error during withdrawal: " + e.getMessage());
        }
    }

    public void transfer(Account fromAccount, Account toAccount, BigDecimal amount) {

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidDepositException();
        }
        BigDecimal fromAccountNewBalance = fromAccount.getBalance().subtract(amount);
        BigDecimal toAccountNewBalance = toAccount.getBalance().add(amount);
        fromAccount.setBalance(fromAccountNewBalance);
        toAccount.setBalance(toAccountNewBalance);
        accountService.updateAccount(fromAccount.getAccountNumber(), fromAccount);
        accountService.updateAccount(toAccount.getAccountNumber(), toAccount);
        transactionRecord = new TransactionRecord(fromAccount.getAccountNumber(), "TRANSFER", amount, fromAccount.getBalance(), "SUCCESS");
        transactionHistory.add(transactionRecord);
        TransactionLogger.logTransaction(transactionRecord);
        transactionRecord = new TransactionRecord(toAccount.getAccountNumber(), "TRANSFER", amount, toAccount.getBalance(), "SUCCESS");
        transactionHistory.add(transactionRecord);
        TransactionLogger.logTransaction(transactionRecord);

        System.out.println(amount + " is successfully transfered to Account number: " + toAccount);

    }
    public static int countTransactionsByDate(String accountNumber) {
        return (int) transactionHistory.stream()
                .filter(transaction -> transaction.getAccountNumber().equals(accountNumber))
                .filter(transaction -> transaction.getTimestamp().toLocalDate().equals(LocalDate.now()))
                .filter(transaction -> transaction.getTransactionType().equals("WITHDRAWAL"))
                .count();
    }
    public void getTransactionHistory(String accountNumber) {
        List<TransactionRecord> filteredTransactions = new ArrayList<>();

        for (TransactionRecord transaction : transactionHistory) {
            if (transaction.getAccountNumber().equals(accountNumber)) {
                filteredTransactions.add(transaction);
            }
        }
        for(TransactionRecord transaction : filteredTransactions)
        {
            System.out.println(transaction);
        }

    }
}
