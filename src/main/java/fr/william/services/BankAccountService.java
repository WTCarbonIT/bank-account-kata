package fr.william.services;

import fr.william.entities.Operation;
import fr.william.exceptions.AmountInvalidException;
import fr.william.exceptions.InsufficientBalanceException;

import java.math.BigDecimal;

public interface BankAccountService {
    Operation deposit(int accountId, BigDecimal amount) throws AmountInvalidException;
    Operation withdraw(int accountId, BigDecimal amount) throws AmountInvalidException, InsufficientBalanceException;
    void printAccountStatement(int accountId);
}
