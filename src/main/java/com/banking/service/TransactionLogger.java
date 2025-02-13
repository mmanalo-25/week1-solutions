package com.banking.service;

import com.banking.model.TransactionRecord;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class TransactionLogger {


        private static final String LOG_DIRECTORY = "src/main/java/com/banking/logs/";
        private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        public static void logTransaction(TransactionRecord transactionRecord) {
            String accountNumber = transactionRecord.getAccountNumber();
            String fileName = LOG_DIRECTORY + accountNumber + ".log";


            File logDir = new File(LOG_DIRECTORY);
            if (!logDir.exists()) {
                logDir.mkdirs();
            }


            String logMessage = String.format("[%s] %s | Amount: %s | Balance: %s | Status: %s\n",
                    transactionRecord.getTimestamp().format(FORMATTER),
                    transactionRecord.getTransactionType(),
                    transactionRecord.getAmount(),
                    transactionRecord.getBalance(),
                    transactionRecord.getStatus());

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
                writer.write(logMessage);
            } catch (IOException e) {
                System.err.println("Failed to log transaction: " + e.getMessage());
            }
        }
}
