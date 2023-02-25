package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.model.BankAccount;

//It is so weird that @Repository is not required here.
public interface BankAccountRepository extends JpaRepository<BankAccount, Long>{

    List<BankAccount> findByUserId(long id);
    
}
