package com.revature.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.revature.model.Transfer;

import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
    
    List<Transfer> findByAccountId(Long accountId);

    //Similar to above function, but also takes a second parameter to further refine your search to only the deposits/withdraws for a given account.
    List<Transfer> findByAccountIdAndIsDeposit(Long traAccountId, Boolean isDeposit);

    //Allows us to get all transfers for an account, by month (and year)
    @Query(value = "select " +
    "* " +
    "from transfer t " +
    "where t.account_id = ?1 " +
    "and extract(year from t.time) = ?2 " +
    "and extract(month from t.time) = ?3 " +
    "order by t.time desc;", nativeQuery = true)
    List<Transfer> getUsingAccountIdAndMonth(Long traAccountId, int year, int month);
}