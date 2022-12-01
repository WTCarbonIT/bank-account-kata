package fr.william;

import fr.william.entities.Operation;
import fr.william.enums.OperationType;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TestFixtures {
    public static Integer sampleAccountId = 1;
    public static LocalDateTime sampleDate = LocalDateTime.now();
    public static Clock sampleClock = Clock.fixed(sampleDate.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
    public static BigDecimal sampleAmount = new BigDecimal("50.00");
    public static BigDecimal sampleNegativeAmount = new BigDecimal("-50.00");
    public static BigDecimal sampleBalance = new BigDecimal("50.00");
    public static BigDecimal sampleDepositBalance = new BigDecimal("100.00");
    public static Operation sampleDepositOperation = new Operation(sampleAccountId, OperationType.DEPOSIT, sampleDate, sampleAmount, sampleDepositBalance);
}
