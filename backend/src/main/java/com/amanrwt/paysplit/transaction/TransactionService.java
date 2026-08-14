package com.amanrwt.paysplit.transaction;

import com.amanrwt.paysplit.budget.Budget;
import com.amanrwt.paysplit.budget.BudgetRepository;
import com.amanrwt.paysplit.auth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BudgetRepository budgetRepository;

    public TransactionResponse addTransaction(User user, String month, String note,
                                                BigDecimal amount, LocalDate date) {
        Budget budget = budgetRepository.findByUserAndMonth(user, month)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Set a salary for " + month + " before adding expenses"));

        Transaction transaction = new Transaction(
                budget, note, amount, date != null ? date : LocalDate.now());

        return new TransactionResponse(transactionRepository.save(transaction));
    }

    public BudgetSummaryResponse getSummary(User user, String month) {
        Budget budget = budgetRepository.findByUserAndMonth(user, month)
                .orElseThrow(() -> new IllegalArgumentException("No budget found for " + month));

        List<Transaction> transactions = transactionRepository.findByBudgetOrderByDateDesc(budget);
        BigDecimal totalSpent = transactionRepository.sumByBudget(budget);

        List<TransactionResponse> transactionResponses = transactions.stream()
                .map(TransactionResponse::new)
                .collect(Collectors.toList());

        return new BudgetSummaryResponse(month, budget.getSalary(), totalSpent, transactionResponses);
    }

    public void deleteTransaction(User user, Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found"));

        if (!transaction.getBudget().getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("You don't have permission to delete this transaction");
        }

        transactionRepository.delete(transaction);
    }

    public List<WeeklySpending> getWeeklyAggregate(User user, String month) {
        return transactionRepository.sumByWeek(user.getId(), month);
    }

    public List<MonthlySpending> getMonthlyAggregate(User user, String year) {
        return transactionRepository.sumByMonth(user.getId(), year + "-%");
    }

    public List<CategorySpending> getCategoryAggregate(User user, String month) {
        return transactionRepository.sumByCategory(user.getId(), month);
    }
}