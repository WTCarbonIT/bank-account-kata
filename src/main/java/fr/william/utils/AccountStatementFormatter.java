package fr.william.utils;

import fr.william.entities.Operation;

import java.util.List;

public interface AccountStatementFormatter {
    String format(List<Operation> operations);
}
