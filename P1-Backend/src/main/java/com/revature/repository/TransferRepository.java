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
     * @param traAccountId
     * @return
     */
    @Query(value = "SELECT * FROM Transfer WHERE traaccountid = ?1", nativeQuery = true)
    List<Transfer> getTransfersByTraAccountId(Long traAccountId);
}
