package fr.william.utils;

import fr.william.entities.Operation;

import java.util.List;

public interface StatementPrinter {
    void print(List<Operation> operations);
}
