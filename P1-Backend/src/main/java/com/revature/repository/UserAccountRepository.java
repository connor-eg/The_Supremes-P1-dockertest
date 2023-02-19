package com.revature.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.model.UserAccount;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    
    @Query("SELECT s FROM UserAccount s WHERE s.username = ?1")
    Optional<UserAccount> findByUsername(String username);

    @Query("SELECT s FROM UserAccount s WHERE s.username = ?1 AND s.password = ?2")
    Optional<UserAccount> findByUsernameAndPassword(String username, String password);

    /*
     * UPDATE products SET price = price * 1.10
        WHERE price <= 99.99
        RETURNING name, price AS new_price;
     */

}
