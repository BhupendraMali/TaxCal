package com.company.taxcal.errorhandler;

public class TaxCalculatorNoValidTransactionException extends  Exception {

    public TaxCalculatorNoValidTransactionException(String errorMessage) {
        super(errorMessage);
    }

}