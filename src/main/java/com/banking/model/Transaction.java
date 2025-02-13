package com.banking.model;

import java.math.BigDecimal;

public interface Transaction {

    public BigDecimal processMonthlyFees()  ;

    public boolean withdraw(BigDecimal amount) ;

    }