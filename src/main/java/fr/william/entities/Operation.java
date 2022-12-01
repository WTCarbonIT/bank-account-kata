package fr.william.entities;

import fr.william.enums.OperationType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Operation {
    private final Integer accountId;
    private final OperationType data;
    private final LocalDateTime date;
    private final BigDecimal amount;
    private final BigDecimal balance;

    public Operation(int accountId, OperationType data, LocalDateTime date, BigDecimal amount, BigDecimal balance) {
        this.accountId = accountId;
        this.data = data;
        this.date = date;
        this.amount = amount;
        this.balance = balance;
    }

    public int getAccountId() {
        return accountId;
    }

    public OperationType getData() {
        return data;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return Objects.equals(accountId, operation.accountId) && data == operation.data && Objects.equals(date, operation.date) && Objects.equals(amount, operation.amount) && Objects.equals(balance, operation.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, data, date, amount, balance);
    }
}
