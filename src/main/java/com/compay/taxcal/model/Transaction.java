package com.compay.taxcal.model;


import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class Transaction {

    private String customerNo;
    private String invoiceNumber;
    private String timeStamp;
    private BigDecimal amount;
    private TaxType taxType;

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }



    public TaxType getTaxType() {
        return taxType;
    }

    public void setTaxType(TaxType taxType) {
        this.taxType = taxType;
    }


    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "customerNo='" + customerNo + '\'' +
                ", invoiceNumber='" + invoiceNumber + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", amount=" + amount +
                ", taxType=" + taxType +
                '}';
    }

}
