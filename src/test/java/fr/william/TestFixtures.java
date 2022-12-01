package fr.william;

import fr.william.entities.Operation;
import fr.william.enums.OperationType;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TestFixtures {
    public static Integer sampleAccountId = 1;
    public static LocalDateTime sampleDate = LocalDateTime.now();
    public static Clock sampleClock = Clock.fixed(sampleDate.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
    public static BigDecimal sampleAmount = new BigDecimal("50.00");
    public static BigDecimal sampleNegativeAmount = new BigDecimal("-50.00");
    public static BigDecimal sampleBalance = new BigDecimal("50.00");
    public static BigDecimal sampleLowBalance = new BigDecimal("10.00");
    public static BigDecimal sampleDepositBalance = new BigDecimal("100.00");
    public static BigDecimal sampleWithdrawalBalance = new BigDecimal("0.00");
    public static Operation sampleDepositOperation = new Operation(sampleAccountId, OperationType.DEPOSIT, sampleDate, sampleAmount, sampleDepositBalance);
    public static Operation sampleWithdrawalOperation = new Operation(sampleAccountId, OperationType.WITHDRAWAL, sampleDate, sampleAmount, sampleWithdrawalBalance);
    public static String sampleFormattedDepositOperation = "|OPERATION|DATE|AMOUNT|BALANCE|\n" +
            "|" + sampleDepositOperation.getData().toString() +
            "|" + sampleDepositOperation.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
            "|" + sampleDepositOperation.getAmount().toString() +
            "|" + sampleDepositOperation.getBalance().toString() +
            "|" + "\n";

    public static String sampleFormattedDepositAndWithdrawalOperation = "|OPERATION|DATE|AMOUNT|BALANCE|\n" +
            "|" + sampleDepositOperation.getData().toString() +
            "|" + sampleDepositOperation.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
            "|" + sampleDepositOperation.getAmount().toString() +
            "|" + sampleDepositOperation.getBalance().toString() +
            "|" + "\n" +
            "|" + sampleWithdrawalOperation.getData().toString() +
            "|" + sampleWithdrawalOperation.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
            "|" + sampleWithdrawalOperation.getAmount().toString() +
            "|" + sampleWithdrawalOperation.getBalance().toString() +
            "|" + "\n";

    public static String sampleFormattedEmptyListOperation =  "|OPERATION|DATE|AMOUNT|BALANCE|\n";
}
