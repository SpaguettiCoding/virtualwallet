package com.alkemy.cysjava.virtualwallet.repositories;

import com.alkemy.cysjava.virtualwallet.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
}
