package com.company.test;

import com.company.taxcal.service.CalculateTaxImpl;
import com.compay.taxcal.model.TaxType;
import com.compay.taxcal.model.Transaction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestCalculateTax {

   @Test
    void testCalculateTaxPerTaxType() throws  Exception {

       CalculateTaxImpl calculateTax = new CalculateTaxImpl();

      BigDecimal tax=  calculateTax.calculateTaxPerTaxType(populateTransactions(), "123" , TaxType.GST);
       assertEquals(new BigDecimal("4"), tax);

   }

   List<Transaction> populateTransactions() {

       List<Transaction> transactions = new ArrayList<>();

       Transaction transaction = new Transaction();
       transaction.setTaxType(TaxType.GST);
       transaction.setCustomerNo("123");
       transaction.setTimeStamp("2021-04-29T13:15:54");
       transaction.setInvoiceNumber("2021/04/customer1-12212");
       transaction.setAmount(BigDecimal.TEN);

       transactions.add(transaction);


       Transaction transaction1 = new Transaction();
       transaction1.setTaxType(TaxType.GST);
       transaction1.setCustomerNo("123");
       transaction1.setTimeStamp("2021-04-29T13:15:54");
       transaction1.setInvoiceNumber("2021/04/customer1-12212");
       transaction1.setAmount(new BigDecimal(30));

       transactions.add(transaction1);

       return  transactions;

   }


}
