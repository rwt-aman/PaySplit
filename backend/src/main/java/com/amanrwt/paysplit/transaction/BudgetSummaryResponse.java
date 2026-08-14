package com.amanrwt.paysplit.transaction;

import java.math.BigDecimal;
import java.util.List;

public class BudgetSummaryResponse {

    private String month;
    private BigDecimal salary;
    private BigDecimal totalSpent;
    private BigDecimal remaining;
    private List<TransactionResponse> transactions;

    public BudgetSummaryResponse(String month, BigDecimal salary, BigDecimal totalSpent,
                                  List<TransactionResponse> transactions) {
        this.month = month;
        this.salary = salary;
        this.totalSpent = totalSpent;
        this.remaining = salary.subtract(totalSpent);
        this.transactions = transactions;
    }

    public String getMonth() { return month; }
    public BigDecimal getSalary() { return salary; }
    public BigDecimal getTotalSpent() { return totalSpent; }
    public BigDecimal getRemaining() { return remaining; }
    public List<TransactionResponse> getTransactions() { return transactions; }
}