package com.company.taxcal.service;

import com.company.taxcal.errorhandler.TaxCalculatorException;
import com.compay.taxcal.model.TaxType;
import com.compay.taxcal.model.Transaction;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface CalculateTax {

    BigDecimal calculateTaxPerTaxType(List<Transaction> transactions, String customerNumber, TaxType taxType) throws TaxCalculatorException;
}
