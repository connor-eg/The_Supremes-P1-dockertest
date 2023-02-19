package com.revature.config;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.revature.model.UserAccount;
import com.revature.repository.UserAccountRepository;

@Configuration
public class UserAccountConfig {
    
    // @Bean
    // CommandLineRunner commandLineRunner(UserAccountRepository repository) {
    //     return args -> {
    //         UserAccount jonathan = new UserAccount("Bankusername", "123456", "Jonathan", "Demaree");      
    //         UserAccount tester = new UserAccount("testusername", "testpassword", "token1234", "Tester", "Testee", "T.", LocalDate.of(1992, 1, 13), "test@test.com", "(555)555-5555");
            
    //         List<UserAccount> userAccountList = new ArrayList<UserAccount>();
    //         userAccountList.add(jonathan);
    //         userAccountList.add(tester);

    //         repository.saveAll(userAccountList);
    //     };
    // }
}
