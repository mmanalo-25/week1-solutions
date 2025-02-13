package com.banking.exception;

public class InvalidDepositException extends BankingException{

    public InvalidDepositException(){
        super("Deposit amount must be greater than zero.");
    }
}
