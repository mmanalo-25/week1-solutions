package com.banking.service;

import com.banking.exception.AccountNotFoundException;
import com.banking.model.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private final Map<String, Account> accounts = new HashMap<>();

    private TransactionRecord transactionRecord;

    public Account createAccount(AccountType accountType, String accountNumber, BigDecimal initialDeposit) {
        Account account;
        switch (accountType) {
            case SAVINGS:
                account = new SavingsAccount(accountType, accountNumber, initialDeposit);
                transactionRecord = new TransactionRecord(accountNumber, "INITIAL DEPOSIT", initialDeposit, initialDeposit, "SUCCESS");
                TransactionService.updateTransactionHistory(transactionRecord);
                TransactionLogger.logTransaction(transactionRecord);
                break;
            case CHECKING:
                account = new CheckingAccount(accountType, accountNumber, initialDeposit);
                transactionRecord = new TransactionRecord(accountNumber, "INITIAL DEPOSIT", initialDeposit, initialDeposit, "SUCCESS");
                TransactionService.updateTransactionHistory(transactionRecord);
                TransactionLogger.logTransaction(transactionRecord);
                break;
            default:
                throw new IllegalArgumentException("Unknown account type");
        }
        accounts.put(accountNumber, account);
        return account;
    }


    public void updateAccount(String accountNumber, Account account) {
        accounts.put(accountNumber, account);
    }


    public boolean accountExist(String accountNumber) {
        return accounts.containsKey(accountNumber);
    }

    public Account getAccount(String accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account == null) {
            throw new AccountNotFoundException(accountNumber);
        }
        return account;
    }


}
