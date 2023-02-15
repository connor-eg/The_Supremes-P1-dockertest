package com.revature.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.revature.model.UserAccount;
import com.revature.repository.UserAccountRepository;

@Configuration
public class UserAccountConfig {
    
    @Bean
    CommandLineRunner commandLineRunner(UserAccountRepository repository) {
        return args -> {
            UserAccount jonathan = new UserAccount("Jonathan", "Demaree", 29, "jonathan136@revature.net", 1234567890);      
            UserAccount tester = new UserAccount("Tester", "Testee", 24, "testtest@revature.net", 123888888);
            
            List<UserAccount> userAccountList = new ArrayList<UserAccount>();
            userAccountList.add(jonathan);
            userAccountList.add(tester);

            repository.saveAll(userAccountList);
        };
    }
}
