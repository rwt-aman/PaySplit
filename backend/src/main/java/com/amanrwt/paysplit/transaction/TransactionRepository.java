package com.amanrwt.paysplit.transaction;

import com.amanrwt.paysplit.budget.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByBudgetOrderByDateDesc(Budget budget);

    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t WHERE t.budget = :budget")
    BigDecimal sumByBudget(@Param("budget") Budget budget);

    @Query(value = "SELECT YEARWEEK(t.date, 1) AS weekNumber, SUM(t.amount) AS total " +
            "FROM transactions t JOIN budgets b ON t.budget_id = b.id " +
            "WHERE b.user_id = :userId AND b.month = :month " +
            "GROUP BY weekNumber ORDER BY weekNumber", nativeQuery = true)
    List<WeeklySpending> sumByWeek(@Param("userId") Long userId, @Param("month") String month);

    @Query("SELECT t.budget.month AS month, SUM(t.amount) AS total FROM Transaction t " +
            "WHERE t.budget.user.id = :userId AND t.budget.month LIKE :yearPrefix " +
            "GROUP BY t.budget.month ORDER BY t.budget.month")
    List<MonthlySpending> sumByMonth(@Param("userId") Long userId, @Param("yearPrefix") String yearPrefix);

    @Query("SELECT MIN(t.note) AS note, SUM(t.amount) AS total FROM Transaction t " +
            "WHERE t.budget.user.id = :userId AND t.budget.month = :month " +
            "GROUP BY LOWER(t.note) ORDER BY total DESC")
    List<CategorySpending> sumByCategory(@Param("userId") Long userId, @Param("month") String month);
}