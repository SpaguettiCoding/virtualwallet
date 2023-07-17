package com.alkemy.cysjava.virtualwallet.repositories;

import com.alkemy.cysjava.virtualwallet.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}