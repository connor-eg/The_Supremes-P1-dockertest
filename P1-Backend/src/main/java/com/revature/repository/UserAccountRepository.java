package com.revature.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.model.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    
}
