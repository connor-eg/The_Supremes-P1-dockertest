package com.revature.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.revature.model.Transfer;

import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
    //For future me: the annotation below is how custom queries work in this dealio.
    //nativeQuery = false makes it so that the query will be parsed as PSQL instead of
    // having Spring try to interpret/validate it (with a different type of SQL).

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
    @Query(value = "SELECT * FROM Transfer WHERE traaccountid = ?1", nativeQuery = true)
    List<Transfer> getTransfersByTraAccountId(Long traAccountId);
}