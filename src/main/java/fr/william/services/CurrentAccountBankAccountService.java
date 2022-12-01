package fr.william.services;

import fr.william.entities.Operation;
import fr.william.enums.OperationType;
import fr.william.exceptions.AmountInvalidException;
import fr.william.exceptions.InsufficientBalanceException;
import fr.william.repositories.BankAccountRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Clock;
import java.time.LocalDateTime;

public class CurrentAccountBankAccountService implements BankAccountService {
    private final BankAccountRepository bankAccountRepository;
    private final Clock clock;

    CurrentAccountBankAccountService(BankAccountRepository bankAccountRepository, Clock clock) {
        this.bankAccountRepository = bankAccountRepository;
        this.clock = clock;
    }

    public CurrentAccountBankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.clock = Clock.systemDefaultZone();
    }

    @Override
    public Operation deposit(int accountId, BigDecimal amount) throws AmountInvalidException {
        amount = amount.setScale(2, RoundingMode.HALF_EVEN);
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new AmountInvalidException();
        }
        BigDecimal balance = bankAccountRepository.getBalance(accountId);
        return createAndSaveOperation(accountId, OperationType.DEPOSIT, amount, balance.add(amount));
    }

    @Override
    public Operation withdraw(int accountId, BigDecimal amount) throws AmountInvalidException, InsufficientBalanceException {
        amount = amount.setScale(2, RoundingMode.HALF_EVEN);
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new AmountInvalidException();
        }
        BigDecimal balance = bankAccountRepository.getBalance(accountId);
        if (amount.compareTo(balance) > 0) {
            throw new InsufficientBalanceException();
        }
        return createAndSaveOperation(accountId, OperationType.WITHDRAWAL, amount, balance.subtract(amount));
    }

    private Operation createAndSaveOperation(int accountId, OperationType withdrawal, BigDecimal amount, BigDecimal balance) {
        Operation operation = new Operation(accountId, withdrawal, LocalDateTime.now(clock), amount, balance);
        operation = bankAccountRepository.addOperation(accountId, operation);
        return operation;
    }
}
