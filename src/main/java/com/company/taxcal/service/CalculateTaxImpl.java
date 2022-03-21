package com.company.taxcal.service;

import com.company.taxcal.errorhandler.TaxCalculatorException;
import com.compay.taxcal.model.TaxType;
import com.compay.taxcal.model.Transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Service

public class CalculateTaxImpl implements  CalculateTax {

    private static final Logger logger = LoggerFactory.getLogger(CalculateTaxImpl.class);
    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);


    /**
     * Calculates 10% tax for given customer for given Tax type
     * @param transactions
     * @param customerNumber
     * @param taxType
     * @return
     * @throws TaxCalculatorException
     */
    @Override
    public BigDecimal calculateTaxPerTaxType(List<Transaction> transactions, String customerNumber, TaxType taxType) throws TaxCalculatorException{
        try {

            List<Transaction> txnForCustomerForGiveTaxType = transactions.stream()
                    .filter(transaction -> transaction.getCustomerNo().equalsIgnoreCase(customerNumber) && transaction.getTaxType().equals(taxType))
                    .collect(Collectors.toList());

            BigDecimal totalTxn = txnForCustomerForGiveTaxType.stream().map(transaction -> transaction.getAmount())
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            logger.info("Total tax for given customer - {}" , totalTxn);
            return  totalTxn.multiply(BigDecimal.TEN).divide(ONE_HUNDRED);

        }catch(Exception tx) {
            logger.error("Exception while calculating tax for customer {}, tax Type {} Exception statck {}", customerNumber, taxType, tx.getStackTrace());
            throw new TaxCalculatorException(tx.getMessage(), tx);
        }


    }

}
