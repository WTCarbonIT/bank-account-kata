package fr.william.services;

import fr.william.entities.Operation;
import fr.william.exceptions.AmountInvalidException;
import fr.william.exceptions.InsufficientBalanceException;
import fr.william.repositories.BankAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static fr.william.TestFixtures.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CurrentAccountBankAccountServiceShould {
    @Mock
    private BankAccountRepository bankAccountRepository;

    private CurrentAccountBankAccountService currentAccountBankAccountService;

    @BeforeEach
    void initialize_bank_account() {
        currentAccountBankAccountService = new CurrentAccountBankAccountService(bankAccountRepository, sampleClock);
    }

    @Test
    void deposit_money_when_amount_is_valid() {
        when(bankAccountRepository.getBalance(sampleAccountId)).thenReturn(sampleBalance);
        when(bankAccountRepository.addOperation(eq(sampleAccountId), any(Operation.class))).thenReturn(sampleDepositOperation);

        Operation output = assertDoesNotThrow(() -> currentAccountBankAccountService.deposit(sampleAccountId, sampleAmount));

        assertThat(output).usingRecursiveComparison().isEqualTo(sampleDepositOperation);
        InOrder order = inOrder(bankAccountRepository);
        order.verify(bankAccountRepository).getBalance(sampleAccountId);
        order.verify(bankAccountRepository).addOperation(sampleAccountId, new Operation(
                sampleDepositOperation.getAccountId(),
                sampleDepositOperation.getData(),
                sampleDepositOperation.getDate(),
                sampleDepositOperation.getAmount(),
                sampleDepositOperation.getBalance()
        ));
        order.verifyNoMoreInteractions();
    }

    @Test
    void throw_amount_invalid_exception_when_amount_is_negative_deposit() {
        assertThrows(AmountInvalidException.class, () -> currentAccountBankAccountService.deposit(sampleAccountId, sampleNegativeAmount));

        verifyNoInteractions(bankAccountRepository);
    }

    @Test
    void withdraw_money_when_amount_is_valid() {
        when(bankAccountRepository.getBalance(sampleAccountId)).thenReturn(sampleBalance);
        when(bankAccountRepository.addOperation(eq(sampleAccountId), any(Operation.class))).thenReturn(sampleWithdrawalOperation);

        Operation output = assertDoesNotThrow(() -> currentAccountBankAccountService.withdraw(sampleAccountId, sampleAmount));

        assertThat(output).usingRecursiveComparison().isEqualTo(sampleWithdrawalOperation);
        InOrder order = inOrder(bankAccountRepository);
        order.verify(bankAccountRepository).getBalance(sampleAccountId);
        order.verify(bankAccountRepository).addOperation(sampleAccountId, new Operation(
                sampleWithdrawalOperation.getAccountId(),
                sampleWithdrawalOperation.getData(),
                sampleWithdrawalOperation.getDate(),
                sampleWithdrawalOperation.getAmount(),
                sampleWithdrawalOperation.getBalance()
        ));
        order.verifyNoMoreInteractions();
    }

    @Test
    void throw_amount_invalid_exception_when_amount_is_negative_withdrawal() {
        assertThrows(AmountInvalidException.class, () -> currentAccountBankAccountService.withdraw(sampleAccountId, sampleNegativeAmount));

        verifyNoInteractions(bankAccountRepository);
    }

    @Test
    void throw_insufficient_balance_exception_when_balance_is_not_enough_withdrawal() {
        when(bankAccountRepository.getBalance(sampleAccountId)).thenReturn(sampleLowBalance);

        assertThrows(InsufficientBalanceException.class, () -> currentAccountBankAccountService.withdraw(sampleAccountId, sampleAmount));

        InOrder order = inOrder(bankAccountRepository);
        order.verify(bankAccountRepository).getBalance(sampleAccountId);
        order.verifyNoMoreInteractions();
    }
}
