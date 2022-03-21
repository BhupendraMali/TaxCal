package com.company.taxcal.mapper;

import com.company.taxcal.errorhandler.TaxCalculatorNoValidTransactionException;
import com.company.taxcal.service.CalculateTaxImpl;
import com.compay.taxcal.model.TaxType;
import com.compay.taxcal.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;

@Component
public class TransactionMapper {

    private static final Logger logger = LoggerFactory.getLogger(TransactionMapper.class);

    /**
     * Maps transactions provided in file to Transaction object
     * @param filePath
     * @return
     * @throws Exception
     */
    public  List<Transaction> mapToTransactions(String filePath) throws Exception {
        List<Transaction> transactions = new ArrayList<Transaction>();
        try {
            Scanner input = new Scanner(new File(filePath));
            while(input.hasNextLine()){
                transactions.add(populateTransaction(input.nextLine()));

            }

            if(transactions.size() == 0) {
                throw new TaxCalculatorNoValidTransactionException("No valid Transactions");
            }

        } catch (FileNotFoundException ex) {
           logger.error("Tax transaction file not found  -{}", ex.getStackTrace());
           throw new FileNotFoundException("File not found");
        }

        return  transactions;
    }

    private Transaction populateTransaction(String transactionLine) {
        Transaction transaction = new Transaction();

        StringTokenizer stringTokenizer = new StringTokenizer(transactionLine, ",");
        if(stringTokenizer.countTokens() == 5) {
            while (stringTokenizer.hasMoreTokens()) {
                transaction.setCustomerNo(stringTokenizer.nextToken().trim());
                transaction.setInvoiceNumber(stringTokenizer.nextToken().trim());
                transaction.setTimeStamp(stringTokenizer.nextToken().trim());
                transaction.setAmount(new BigDecimal(stringTokenizer.nextToken().trim()));
                transaction.setTaxType(TaxType.valueOf(stringTokenizer.nextToken().trim()));

            }
        } else{
            logger.warn("Transactions with invalid input - {}" , transaction);
        }
        logger.debug("Transaction : - {}", transaction);
        return  transaction;
    }
}
