package com.company.taxcal;

import com.company.taxcal.errorhandler.TaxCalculatorException;
import com.company.taxcal.errorhandler.TaxCalculatorNoValidTransactionException;
import com.company.taxcal.mapper.TransactionMapper;
import com.compay.taxcal.model.TaxType;
import com.company.taxcal.service.CalculateTax;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ExitCodeExceptionMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.math.BigDecimal;

import static java.lang.System.exit;



@SpringBootApplication
@ComponentScan("com.company.*")
public class TaxCalculatorApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(TaxCalculatorApplication.class);

    @Autowired
    private CalculateTax calculateTax;

    @Autowired
    private TransactionMapper transactionMapper;

    public static void main(String[] args) throws Exception {

        SpringApplication app = new SpringApplication(TaxCalculatorApplication.class);
        app.run(args);

    }


    @Override
    public void run(String... args) throws Exception {

        if(args.length < 3) {
            throw new IllegalArgumentException("Illegal argument received");
        }

        BigDecimal taxAmount = calculateTax.calculateTaxPerTaxType(transactionMapper.mapToTransactions(args[2]), args[1], TaxType.valueOf(args[0]));

        logger.info("For Tax {} Customer {} had declared {}", TaxType.valueOf(args[0]), args[1].trim(), taxAmount);

        exit(0);
    }


    /**
     * This method returns the exception code for particular exception thrown, so the calling client can handle this
     * @return
     */
    @Bean
    public ExitCodeExceptionMapper exceptionBasedExitCode() {
        return exception -> {
            if (exception.getCause() instanceof FileNotFoundException) {
                return 10;
            }

            if (exception.getCause() instanceof NumberFormatException) {
                return 20;
            }

            if (exception.getCause() instanceof IllegalArgumentException) {
                return 30;
            }

            if (exception.getCause() instanceof TaxCalculatorException) {
                return 40;
            }

            if (exception.getCause() instanceof TaxCalculatorNoValidTransactionException) {
                return 50;
            }
            return 99;
        };
    }



}
