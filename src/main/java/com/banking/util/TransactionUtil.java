package com.banking.util;

import java.math.BigDecimal;

public class TransactionUtil {

    public static BigDecimal isValidBigDecimal(String input) throws NumberFormatException {
        try {
            return new BigDecimal(input);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid amount. Please enter a valid number.");
        }
    }
}
