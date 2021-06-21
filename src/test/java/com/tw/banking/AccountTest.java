package com.tw.banking;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class AccountTest {
    @Test
    void should_call_repository_addDeposit_when_deposit_given_amount() {
        TransactionRepository transactionRepository = Mockito.mock(TransactionRepository.class);
        Printer printer = Mockito.mock(Printer.class);
        Account account = new Account(transactionRepository, printer);
        int amount = 100;

        account.deposit(amount);

        verify(transactionRepository, times(1)).addDeposit(eq(amount));
    }

    @Test
    void should_call_repository_addWithdraw_when_withdraw_given_amount() {
        TransactionRepository transactionRepository = Mockito.mock(TransactionRepository.class);
        Printer printer = Mockito.mock(Printer.class);
        Account account = new Account(transactionRepository, printer);
        int amount = 100;

        account.withdraw(amount);

        verify(transactionRepository, times(1)).addWithdraw(eq(amount));
    }

    @Test
    void should_print_transaction_when_printStatement_given_transaction_is_test() {
        TransactionRepository transactionRepository = Mockito.mock(TransactionRepository.class);
        Printer printer = Mockito.mock(Printer.class);
        Account account = new Account(transactionRepository, printer);
        List<Transaction> transactions = new ArrayList<>();
        doReturn(transactions).when(transactionRepository).allTransactions();

        account.printStatement();

        verify(printer, times(1)).print(eq(transactions));
    }
}