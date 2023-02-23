package com.revature.config;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.revature.model.BankAccount;
import com.revature.model.Transfer;
import com.revature.repository.BankAccountRepository;
import com.revature.repository.TransferRepository;
import com.revature.service.TransferService;

@Configuration
public class TransferConfig {

    @Bean
    CommandLineRunner transferCLR(TransferRepository tRepository, BankAccountRepository baRepository, TransferService service) {
        return args -> {

            baRepository.save(new BankAccount(
                BankAccount.AccType.CHECKING, new BigDecimal("30.02"), 1L));
            baRepository.save(new BankAccount(
                BankAccount.AccType.SAVING, new BigDecimal("2025.03"), 1L));

            Transfer t1 = new Transfer(
                1L,
                new BigDecimal("20.0"),
                true,
                "Found a $20 bill on the road"
            );

            Transfer t2 = new Transfer(
                1L,
            new BigDecimal("1.60"),
            false,
            "Pack of gum"
            );

            tRepository.saveAll(
                Arrays.asList(t1, t2)
            );


            service.sendMoneyBetweenTwoAccounts(1L, 2L, new BigDecimal(250));
        };
    }
}
