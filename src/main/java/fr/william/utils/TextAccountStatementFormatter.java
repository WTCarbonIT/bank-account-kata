package fr.william.utils;

import fr.william.entities.Operation;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class TextAccountStatementFormatter implements AccountStatementFormatter {
    public TextAccountStatementFormatter() {
    }

    @Override
    public String format(List<Operation> operations) {
        String attributes = "|OPERATION|DATE|AMOUNT|BALANCE|\n";
        String details = operations
                .stream()
                .map(o -> "|" + o.getData().toString() +
                        "|" + o.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                        "|" +o.getAmount().toString() +
                        "|" +o.getBalance().toString() +
                        "|" + "\n")
                .collect(Collectors.joining());
        return attributes + details;
    }
}
