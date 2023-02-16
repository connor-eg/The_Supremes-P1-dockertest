package com.revature.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.model.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

}
