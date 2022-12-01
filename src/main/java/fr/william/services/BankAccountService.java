package fr.william.services;

import fr.william.entities.Operation;

import java.math.BigDecimal;

public interface BankAccountService {
    Operation deposit(int accountId, BigDecimal amount);
}
