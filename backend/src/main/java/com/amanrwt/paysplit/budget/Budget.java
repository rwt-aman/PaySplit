package com.amanrwt.paysplit.budget;

import com.amanrwt.paysplit.auth.User;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "budgets", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "month"})
})
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String month;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal salary;

    public Budget() {}

    public Budget(User user, String month, BigDecimal salary) {
        this.user = user;
        this.month = month;
        this.salary = salary;
    }

    public Long getId() { return id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public String getMonth() { return month; }
    public void setMonth(String month) { this.month = month; }
    public BigDecimal getSalary() { return salary; }
    public void setSalary(BigDecimal salary) { this.salary = salary; }
}