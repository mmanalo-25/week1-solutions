//package com.banking.util;
//
//public class AccountNumberGenerator {
//    private static final int ACCOUNT_NUMBER_LENGTH = 10; // Adjust length as needed
//    private static final SecureRandom RANDOM = new SecureRandom();
//    private static final Set<String> generatedAccountNumbers = new HashSet<>();
//
//    public static String generateUniqueAccountNumber() {
//        String accountNumber;
//        do {
//            accountNumber = generateRandomAccountNumber();
//        } while (!generatedAccountNumbers.add(accountNumber)); // Keep generating until unique
//
//        return accountNumber;
//    }
//
//    private static String generateRandomAccountNumber() {
//        StringBuilder accountNumber = new StringBuilder();
//        for (int i = 0; i < ACCOUNT_NUMBER_LENGTH; i++) {
//            accountNumber.append(RANDOM.nextInt(10)); // Generates a digit (0-9)
//        }
//        return accountNumber.toString();
//    }
//}
