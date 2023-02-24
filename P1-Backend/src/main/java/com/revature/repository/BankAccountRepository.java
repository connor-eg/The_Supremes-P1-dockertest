package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.model.BankAccount;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long>{

    @Query(value = "SELECT b FROM BankAccount b WHERE b.bacuserid = ?1")
    List<BankAccount> getBankAccountsByUserId(Long uid);
    
}
