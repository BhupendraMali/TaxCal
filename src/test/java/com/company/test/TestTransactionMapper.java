package com.company.test;

import com.company.taxcal.errorhandler.TaxCalculatorNoValidTransactionException;
import com.company.taxcal.mapper.TransactionMapper;
import com.compay.taxcal.model.Transaction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestTransactionMapper {

    TransactionMapper transactionMapper =  new TransactionMapper();

    @Test
    public void testPopulateTransaction() {

        String transaction = "123, 2021/04/customer1-12212, 2021-04-29T13:15:54, 10.00, GST";
        Transaction transaction1 =  ReflectionTestUtils.invokeMethod(transactionMapper, "populateTransaction", transaction);

        assertEquals(10.00, transaction1.getAmount());
        assertEquals("GST", transaction1.getTaxType().toString());
    }


    @Test
    public void testMapToTransactions() throws  Exception{

        String filepath = "src/test/resources/123tax.txt";
        TransactionMapper transactionMapper = new TransactionMapper();
        List<Transaction> transactionList = transactionMapper.mapToTransactions(filepath);
        assertEquals(3, transactionList.size());

    }

    @Test()
    public void testMapToTransactionsForException(){

        String filepath = "src/test/resources/123tax.124";
        Assertions.assertThrows(FileNotFoundException.class, () -> {
            TransactionMapper transactionMapper = new TransactionMapper();
            List<Transaction> transactionList = transactionMapper.mapToTransactions(filepath);
        });

    }


    @Test()
    public void testMapToTransactionsForTaxCalculatorNoValidTransactionException() {

        String filepath = "src/test/resources/emptyTax.txt";
        Assertions.assertThrows(TaxCalculatorNoValidTransactionException.class, () -> {
            TransactionMapper transactionMapper = new TransactionMapper();
            List<Transaction> transactionList = transactionMapper.mapToTransactions(filepath);
        });

    }



}
