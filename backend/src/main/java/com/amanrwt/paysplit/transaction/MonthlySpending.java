package com.amanrwt.paysplit.transaction;

import java.math.BigDecimal;

public interface MonthlySpending {
    String getMonth();
    BigDecimal getTotal();
}