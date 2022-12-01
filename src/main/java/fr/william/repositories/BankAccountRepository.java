package fr.william.repositories;

import fr.william.entities.Operation;

import java.math.BigDecimal;
import java.util.List;

public interface BankAccountRepository {
    List<Operation> findAllOperations(int id);
    Operation addOperation(int id, Operation operation);
    BigDecimal getBalance(int id);
}
