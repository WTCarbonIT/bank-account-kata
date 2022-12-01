package fr.william.utils;

import fr.william.entities.Operation;

import java.util.List;

public class TerminalStatementPrinter implements StatementPrinter {
    private final TextAccountStatementFormatter textAccountStatementFormatter;
    public TerminalStatementPrinter(TextAccountStatementFormatter textAccountStatementFormatter) {
        this.textAccountStatementFormatter = textAccountStatementFormatter;
    }
    @Override
    public void print(List<Operation> operations) {
        System.out.print(textAccountStatementFormatter.format(operations));
    }
}
