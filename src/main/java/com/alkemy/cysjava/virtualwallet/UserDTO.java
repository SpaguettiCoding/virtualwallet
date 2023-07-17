package com.alkemy.cysjava.virtualwallet;

import com.alkemy.cysjava.virtualwallet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDTO extends JpaRepository<User, Long> {

}
