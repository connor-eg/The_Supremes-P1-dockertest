package com.revature.config;

import java.math.BigDecimal;

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
    CommandLineRunner transferCLR(TransferRepository tRepository, BankAccountRepository baRepository, TransferService tService) {
        return args -> {

            baRepository.save(new BankAccount(
                1L,
                BankAccount.AccType.CHECKING));
            baRepository.save(new BankAccount(
                1L,
                BankAccount.AccType.SAVINGS));

            Transfer t1 = new Transfer(
                1L,
                new BigDecimal("200.0"),
                true,
                "Found a $200 bill on the road"
            );

            Transfer t2 = new Transfer(
                1L,
            new BigDecimal("1.60"),
            false,
            "Pack of gum"
            );

            tService.addNewTransfer(t1);
            tService.addNewTransfer(t2);

            tService.sendMoneyBetweenTwoAccounts(1L, 2L, new BigDecimal(30));
        };
    }

}
