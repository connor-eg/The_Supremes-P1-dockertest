package com.revature.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.revature.model.BankAccount;

//It is so weird that @Repository is not required here.
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    List<BankAccount> findByUserId(long id);

    Optional<BankAccount> findById(long id);

    @Modifying
    @Query("update BankAccount b set b.balance = ?2 where b.id = ?1")
    void updateBalance(long id, BigDecimal balance);

}
