package com.revature.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.revature.model.Transfer;

import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
    /**
     * Gets all of the transfers associated with this account.
     *  TODO: 
     * when we have validation, the account we're checking transfers for should belong
     * to the account holder (i.e. you should not be able to see the activity of an
     * account that you do not own).
     * 
     * @param traAccountId The account to check transfers for
     * @return All transfers associated with this account
     */
    @Query(value = "SELECT t FROM Transfer t WHERE t.traaccountid = ?1")
    List<Transfer> getTransfersByTraAccountId(Long traAccountId);

    //Similar to above function, but also takes a second parameter to further refine your search to only the deposits/withdraws for a given account.
    @Query(value = "SELECT t FROM Transfer t WHERE t.traaccountid = ?1 AND t.traisdeposit = ?2")
    List<Transfer> getTransfersByTraAccountId(Long traAccountId, Boolean isDeposit);

    //Allows us to get all transfers for an account, by month
    @Query(value = "select " +
    "* " +
    "from transfer t " +
    "where traaccountid = ?1 " +
    "and extract(year from tratime) = ?2 " +
    "and extract(month from tratime) = ?3 " +
    "order by tratime desc;", nativeQuery = true)
    List<Transfer> getTransfersByAccountIdAndYearAndMonth(Long traAccountId, int year, int month);
}