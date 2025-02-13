package com.banking.app;

import com.banking.exception.AccountNotFoundException;
import com.banking.exception.InvalidDepositException;
import com.banking.model.*;
import com.banking.service.AccountService;
import com.banking.service.TransactionService;
import com.banking.util.TransactionUtil;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);


    static AccountService accountService = new AccountService();
    static TransactionService transactionService = new TransactionService(accountService);
    static Account account;

    public static void main(String[] args) {
        mainDashboard();
    }

    public static void mainDashboard() {

        System.out.println("What to do?");
        System.out.println("1. Create Account");
        System.out.println("2. Transaction");
        System.out.print("Enter your choice: ");

        switch (input.nextLine()) {
            case "1":
                createAccountDashboard();
                break;
            case "2":
                System.out.println("TRANSACTION");
                do {
                    try {
                        System.out.print("Enter Account Number: ");
                        account = accountService.getAccount(input.nextLine());

                    } catch (AccountNotFoundException e) {
                        System.out.println("ERROR: " + e.getMessage());
                    }
                } while (account == null);
                transactionDashboard(account);
                break;

            default:
                System.out.println("Invalid Selection. Choose between 1-2 only.");
                break;

        }


    }

    public static void createAccountDashboard() {
        Account account;
        String accountNumber;
        AccountType accountType;
        BigDecimal deposit;

        do {
            System.out.println("CREATE ACCOUNT:");
            System.out.println("1. Savings Account ");
            System.out.println("2. Checking Account");
            System.out.println("3. Back");
            System.out.print("Enter your choice: ");
            switch (input.nextLine()) {
                case "1":
                    accountType = AccountType.SAVINGS;
                    System.out.println(accountType);
                    do {
                        System.out.print("Enter Account Number: " + accountType.getCode());
                        String inputAccount = input.nextLine();
                        accountNumber = accountType.getCode() + inputAccount;

                        boolean exists = accountService.accountExist(accountNumber);

                        if (exists) {
                            System.out.println("Account number already used. Try again.");
                        }
                    } while (accountService.accountExist(accountNumber));

                    System.out.print("Enter initial deposit: ");
                    deposit = input.nextBigDecimal();
                    accountService.createAccount(accountType, accountNumber, deposit);
                    System.out.println("Account successfully created.\n");
                    input.nextLine();
                    break;

                case "2":
                    accountType = AccountType.CHECKING;
                    System.out.println(accountType);
                    do {
                        System.out.print("Enter Account Number: " + accountType.getCode());
                        String inputAccount = input.nextLine();
                        accountNumber = accountType.getCode() + inputAccount;

                        boolean exists = accountService.accountExist(accountNumber);

                        if (exists) {
                            System.out.println("Account number already used. Try again.");
                        }
                    } while (accountService.accountExist(accountNumber));

                    System.out.print("Enter initial deposit: ");
                    deposit = input.nextBigDecimal();
                    accountService.createAccount(accountType, accountNumber, deposit);
                    System.out.println("Account successfully created.\n");

                    input.nextLine();
                    break;

                case "3":
                    System.out.println("Return to Main Menu");
                    mainDashboard();
                    return;

                default:
                    System.out.println("Select between 1 - 3 only.");
                    break;
            }
        } while (true);
    }

    public static void transactionDashboard(Account account) {
        BigDecimal amount;
        do {

            System.out.println("1. Check balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdrawal");
            System.out.println("4. Transfer to another account");
            System.out.println("5. Process monthly fee");
            System.out.println("6. Transaction history");
            System.out.println("7. Back");
            System.out.print("Enter your choice: ");

            switch (input.nextLine()) {
                case "1":
                    System.out.println("CHECK BALANCE");
                    System.out.println("BALANCE: " + account.getBalance());
                    break;
                case "2":
                    System.out.println("DEPOSIT");
                    System.out.print("Enter amount: ");

                    try {
                        amount = TransactionUtil.isValidBigDecimal(input.nextLine());
                        transactionService.deposit(account.getAccountNumber(), amount);

                    } catch (InvalidDepositException | InputMismatchException | NumberFormatException e) {
                        System.out.println("Error: " + e.getMessage());
                        System.out.println("Returning to Transaction Dashboard...");
                    }

                    break;
                case "3":
                    System.out.println("WITHDRAW");
                    System.out.print("Enter amount: ");
                    try {

                        amount = TransactionUtil.isValidBigDecimal(input.nextLine());
                        transactionService.withdraw(account.getAccountNumber(), amount);

                    } catch (InvalidDepositException | InputMismatchException | NumberFormatException e) {
                        System.out.println("Error: " + e.getMessage());
                        System.out.println("Returning to Transaction Dashboard...");
                    }
                    break;
                case "4":
                    System.out.println("TRANSFER TO ANOTHER ACCOUNT");
                    System.out.println("FROM : " + account.getAccountNumber());
                    System.out.println("BALANCE: " + account.getBalance());
                    System.out.println("TO: ");
                    Account accountReceiver;
                    do {
                        System.out.print("Enter Account Number: ");
                        accountReceiver = accountService.getAccount(input.nextLine());
                    } while (accountReceiver == null);
                    System.out.print("Enter amount: ");
                    try {

                        amount = TransactionUtil.isValidBigDecimal(input.nextLine());
                        transactionService.transfer(account, accountReceiver, amount);

                    } catch (InvalidDepositException | InputMismatchException | NumberFormatException e) {
                        System.out.println("Error: " + e.getMessage());
                        System.out.println("Returning to Transaction Dashboard...");
                    }

                    break;
                case "5":
                    System.out.println("PROCESS MONTHLY INTEREST");
                    transactionService.processMonthlyFees(account.getAccountNumber());
                    break;
                case "6":
                    System.out.println("TRANSACTION HISTORY");
                    transactionService.getTransactionHistory(account.getAccountNumber());
                    break;
                case "7":
                    mainDashboard();
                    break;
                default:
                    System.out.println("Invalid Selection. Select between 1-4 only.");

            }
        } while (true);

    }

}
