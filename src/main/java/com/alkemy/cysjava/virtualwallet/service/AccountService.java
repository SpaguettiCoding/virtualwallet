package com.alkemy.cysjava.virtualwallet.service;

import com.alkemy.cysjava.virtualwallet.models.Account;
import com.alkemy.cysjava.virtualwallet.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
}
