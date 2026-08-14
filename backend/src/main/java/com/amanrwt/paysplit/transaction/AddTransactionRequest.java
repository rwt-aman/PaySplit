package com.amanrwt.paysplit.transaction;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AddTransactionRequest {

    @NotBlank
    private String month;

    @NotBlank
    private String note;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than 0")
    private BigDecimal amount;

    private LocalDate date;

    public String getMonth() { return month; }
    public void setMonth(String month) { this.month = month; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}