package com.amanrwt.paysplit.budget;

import com.amanrwt.paysplit.auth.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/budget")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    // Pulls the logged-in user from the JWT filter's SecurityContext --
    // never from anything the client sends in the request body.
    private User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @PostMapping("/salary")
    public ResponseEntity<BudgetResponse> setSalary(@Valid @RequestBody SetSalaryRequest request) {
        Budget budget = budgetService.setSalary(getCurrentUser(), request.getMonth(), request.getSalary());
        return ResponseEntity.ok(new BudgetResponse(budget));
    }

    @GetMapping("/{month}")
    public ResponseEntity<BudgetResponse> getBudget(@PathVariable String month) {
        Budget budget = budgetService.getBudget(getCurrentUser(), month);
        return ResponseEntity.ok(new BudgetResponse(budget));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBadRequest(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}