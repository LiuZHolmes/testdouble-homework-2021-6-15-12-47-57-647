package com.tw.banking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class PrinterTest {
    @Test
    void should_print_transactions_when_print_given_transactions() {
        Console console = Mockito.mock(Console.class);
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        Printer printer = new Printer(console);
        Transaction transaction = new Transaction("01/11/2021", 100);
        Transaction secondTransaction = new Transaction("01/11/2022", 200);
        Transaction thirdTransaction = new Transaction("01/11/2023", -100);
        List<Transaction> transactions = List.of(transaction, secondTransaction, thirdTransaction);

        printer.print(transactions);

        verify(console, times(4)).printLine(stringArgumentCaptor.capture());
        List<String> printLines = stringArgumentCaptor.getAllValues();
        Assertions.assertEquals("DATE | AMOUNT | BALANCE", printLines.get(0));
        Assertions.assertEquals("01/11/2023 | -100 | 200", printLines.get(1));
        Assertions.assertEquals("01/11/2022 | 200 | 300", printLines.get(2));
        Assertions.assertEquals("01/11/2021 | 100 | 100", printLines.get(3));
    }

}