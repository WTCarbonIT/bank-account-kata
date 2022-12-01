package fr.william.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static fr.william.TestFixtures.*;
import static fr.william.TestFixtures.sampleFormattedEmptyListOperation;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TextAccountStatementFormatterShould {
    private TextAccountStatementFormatter textAccountStatementFormatter;

    @BeforeEach
    void initialize_formatter() {
        textAccountStatementFormatter = new TextAccountStatementFormatter();
    }

    @Test
    void return_formatted_operation() {
        assertEquals(sampleFormattedDepositOperation, textAccountStatementFormatter.format(List.of(sampleDepositOperation)));
    }

    @Test
    void return_formatted_operations_list() {
        assertEquals(sampleFormattedDepositAndWithdrawalOperation, textAccountStatementFormatter.format(List.of(sampleDepositOperation, sampleWithdrawalOperation)));
    }

    @Test
    void return_formatted_empty_list() {
        assertEquals(sampleFormattedEmptyListOperation, textAccountStatementFormatter.format(Collections.emptyList()));
    }
}
