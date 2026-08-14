package com.amanrwt.paysplit.transaction;

import java.math.BigDecimal;

public interface WeeklySpending {
    Long getWeekNumber();
    BigDecimal getTotal();
}