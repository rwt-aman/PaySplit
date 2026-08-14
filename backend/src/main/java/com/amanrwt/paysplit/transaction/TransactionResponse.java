package com.amanrwt.paysplit.transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionResponse {

    private Long id;
    private String note;
    private BigDecimal amount;
    private LocalDate date;

    public TransactionResponse(Transaction transaction) {
        this.id = transaction.getId();
        this.note = transaction.getNote();
        this.amount = transaction.getAmount();
        this.date = transaction.getDate();
    }

    public Long getId() { return id; }
    public String getNote() { return note; }
    public BigDecimal getAmount() { return amount; }
    public LocalDate getDate() { return date; }
}