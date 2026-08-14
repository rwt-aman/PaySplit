package com.amanrwt.paysplit.budget;

import java.math.BigDecimal;

/**
 * Safe DTO returned by BudgetController so Jackson never tries to serialize
 * the lazy-loaded User entity inside Budget, which causes a
 * LazyInitializationException / stack-overflow at runtime.
 */
public class BudgetResponse {

    private Long id;
    private String month;
    private BigDecimal salary;

    public BudgetResponse(Budget budget) {
        this.id = budget.getId();
        this.month = budget.getMonth();
        this.salary = budget.getSalary();
    }

    public Long getId() { return id; }
    public String getMonth() { return month; }
    public BigDecimal getSalary() { return salary; }
}
