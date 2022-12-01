package fr.william.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static fr.william.TestFixtures.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TerminalStatementPrinterShould {
    @Mock
    private TextAccountStatementFormatter textAccountStatementFormatter;
    @InjectMocks
    private TerminalStatementPrinter terminalStatementPrinter;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void print_list_of_operation() {
        when(textAccountStatementFormatter.format(List.of(sampleDepositOperation))).thenReturn(sampleFormattedDepositOperation);

        terminalStatementPrinter.print(List.of(sampleDepositOperation));

        assertEquals(sampleFormattedDepositOperation, outContent.toString());
        InOrder order = inOrder(textAccountStatementFormatter);
        order.verify(textAccountStatementFormatter).format(List.of(sampleDepositOperation));
        order.verifyNoMoreInteractions();
    }
}
