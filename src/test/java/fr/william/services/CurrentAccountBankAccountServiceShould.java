package fr.william.services;

import fr.william.entities.Operation;
import fr.william.repositories.BankAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static fr.william.TestFixtures.*;
import static fr.william.TestFixtures.sampleDepositOperation;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

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

}
