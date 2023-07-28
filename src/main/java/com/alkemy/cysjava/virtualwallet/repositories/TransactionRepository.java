package com.alkemy.cysjava.virtualwallet.repositories;

import com.alkemy.cysjava.virtualwallet.models.Account;
import com.alkemy.cysjava.virtualwallet.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT a FROM Transaction a WHERE a.account.id = :accountId")
    List<Transaction> findTransactionsByAccount(@Param("accountId") Long accountId);
}
