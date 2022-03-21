package com.company.taxcal.errorhandler;

public class TaxCalculatorException extends  Exception {

    public TaxCalculatorException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

}
