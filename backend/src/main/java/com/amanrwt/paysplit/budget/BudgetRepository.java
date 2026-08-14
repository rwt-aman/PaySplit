package com.amanrwt.paysplit.budget;

import com.amanrwt.paysplit.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    Optional<Budget> findByUserAndMonth(User user, String month);
}