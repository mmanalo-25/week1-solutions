package com.banking.model;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionRecord {

    private String accountNumber;
    private String transactionType; // "Deposit", "Withdrawal", "Transfer"
    private BigDecimal amount;
    private LocalDateTime timestamp;
    private String status; // "Success", "Failed"
    private BigDecimal balance;

    public TransactionRecord( String accountNumber, String transactionType, BigDecimal amount, BigDecimal balance, String status) {

        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.balance = balance;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getStatus() {
        return status;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getAccountNumber(){
        return this.accountNumber;
    }
    public String getFormattedTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return timestamp.format(formatter);
    }

    public String toString() {
        return getFormattedTimestamp() + " | " + transactionType + " | Amount: " + amount + " | Balance: " + balance + " | Status: " + status ;
    }
jjj
}
