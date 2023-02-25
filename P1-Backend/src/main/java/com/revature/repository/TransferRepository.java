package com.revature.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.revature.model.Transfer;

import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
    /**
     *  TODO: 
     * when we have validation, the account we're checking transfers for should belong
     * to the account holder (i.e. you should not be able to see the activity of an
     * account that you do not own).
     */

    List<Transfer> findByAccountId(Long accountId);

    //Similar to above function, but also takes a second parameter to further refine your search to only the deposits/withdraws for a given account.
    List<Transfer> findByAccountIdAndIsDeposit(Long traAccountId, Boolean isDeposit);

    //Allows us to get all transfers for an account, by month (and year)
    @Query(value = "select " +
    "* " +
    "from transfer t " +
    "where traaccountid = ?1 " +
    "and extract(year from tratime) = ?2 " +
    "and extract(month from tratime) = ?3 " +
    "order by tratime desc;", nativeQuery = true)
    List<Transfer> getUsingAccountIdAndMonth(Long traAccountId, int year, int month);
}