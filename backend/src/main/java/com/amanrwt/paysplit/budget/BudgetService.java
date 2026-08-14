package com.amanrwt.paysplit.budget;

import com.amanrwt.paysplit.auth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    public Budget setSalary(User user, String month, BigDecimal salary) {
        Budget budget = budgetRepository.findByUserAndMonth(user, month)
                .orElse(new Budget(user, month, salary));
        budget.setSalary(salary);
        return budgetRepository.save(budget);
    }

    public Budget getBudget(User user, String month) {
        return budgetRepository.findByUserAndMonth(user, month)
                .orElseThrow(() -> new IllegalArgumentException("No budget found for " + month));
    }
}