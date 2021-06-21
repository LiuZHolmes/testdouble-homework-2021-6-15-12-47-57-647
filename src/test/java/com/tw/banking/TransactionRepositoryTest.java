package com.tw.banking;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

class TransactionRepositoryTest {
    @Test
    void should_add_new_deposit_transaction_when_addDeposit_given_amount() {
        Clock clock = Mockito.mock(Clock.class);
        TransactionRepository transactionRepository = new TransactionRepository(clock);
        int amount = 100;
        String todayAsString = "01/11/2021";
        doReturn(todayAsString).when(clock).todayAsString();

        transactionRepository.addDeposit(amount);

        Transaction transaction = transactionRepository.allTransactions().get(0);
        assertEquals(todayAsString, transaction.date());
        assertEquals(amount, transaction.amount());
    }

    @Test
    void should_add_new_withdraw_transaction_when_addWithdraw_given_amount() {
        Clock clock = Mockito.mock(Clock.class);
        TransactionRepository transactionRepository = new TransactionRepository(clock);
        int amount = 100;
        String todayAsString = "01/11/2021";
        doReturn(todayAsString).when(clock).todayAsString();

        transactionRepository.addWithdraw(amount);

        Transaction transaction = transactionRepository.allTransactions().get(0);
        assertEquals(todayAsString, transaction.date());
        assertEquals(-amount, transaction.amount());
    }

    @Test
    void should_return_unmodifiable_list_when_allTransactions_given_amount() {
        Clock clock = Mockito.mock(Clock.class);
        TransactionRepository transactionRepository = new TransactionRepository(clock);

        List<Transaction> transactions = transactionRepository.allTransactions();

        assertThrows(UnsupportedOperationException.class
                , () -> transactions.add(new Transaction("01/11/2021", 100)));
    }

}