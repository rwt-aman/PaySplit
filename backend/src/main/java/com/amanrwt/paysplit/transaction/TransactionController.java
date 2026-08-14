package com.amanrwt.paysplit.transaction;

import com.amanrwt.paysplit.auth.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    private User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @PostMapping
    public ResponseEntity<TransactionResponse> addTransaction(@Valid @RequestBody AddTransactionRequest request) {
        TransactionResponse response = transactionService.addTransaction(
                getCurrentUser(), request.getMonth(), request.getNote(),
                request.getAmount(), request.getDate());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/summary/{month}")
    public ResponseEntity<BudgetSummaryResponse> getSummary(@PathVariable String month) {
        return ResponseEntity.ok(transactionService.getSummary(getCurrentUser(), month));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(getCurrentUser(), id);
        return ResponseEntity.ok("Transaction deleted successfully");
    }

    @GetMapping("/aggregate/weekly/{month}")
    public ResponseEntity<List<WeeklySpending>> getWeekly(@PathVariable String month) {
        return ResponseEntity.ok(transactionService.getWeeklyAggregate(getCurrentUser(), month));
    }

    @GetMapping("/aggregate/monthly/{year}")
    public ResponseEntity<List<MonthlySpending>> getMonthly(@PathVariable String year) {
        return ResponseEntity.ok(transactionService.getMonthlyAggregate(getCurrentUser(), year));
    }

    @GetMapping("/aggregate/category/{month}")
    public ResponseEntity<List<CategorySpending>> getCategory(@PathVariable String month) {
        return ResponseEntity.ok(transactionService.getCategoryAggregate(getCurrentUser(), month));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBadRequest(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}