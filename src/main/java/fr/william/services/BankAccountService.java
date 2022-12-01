package fr.william.services;

import fr.william.entities.Operation;
import fr.william.exceptions.AmountInvalidException;

import java.math.BigDecimal;

public interface BankAccountService {
    Operation deposit(int accountId, BigDecimal amount) throws AmountInvalidException;
}
