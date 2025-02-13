package com.banking.util;

import java.math.BigDecimal;

public class TransactionUtil {

    public static boolean isValidAmount(BigDecimal amount) {
        // Check if amount is null or less than or equal to zero
        return amount != null && amount.compareTo(BigDecimal.ZERO) > 0;
    }

    public static BigDecimal isValidBigDecimal(String input) throws NumberFormatException {
        try {
            return new BigDecimal(input);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid amount. Please enter a valid number.");
        }
    }
}
