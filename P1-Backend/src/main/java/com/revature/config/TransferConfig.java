package com.revature.config;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.revature.model.Transfer;
import com.revature.repository.TransferRepository;

@Configuration
public class TransferConfig {

    @Bean
    CommandLineRunner transferCLR(TransferRepository repository) {
        return args -> {
            Transfer t1 = new Transfer(
                1L,
                new BigDecimal("20.0"),
                true,
                new Timestamp(new Date().getTime()),
                "Found a $20 bill on the road"
            );

            Transfer t2 = new Transfer(
                1L,
            new BigDecimal("1.60"),
            false,
            new Timestamp(new Date().getTime()),
            "Pack of gum"
            );

            repository.saveAll(
                Arrays.asList(t1, t2)
            );
        };
    }
}
