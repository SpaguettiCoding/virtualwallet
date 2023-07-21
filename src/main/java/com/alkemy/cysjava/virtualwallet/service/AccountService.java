package com.alkemy.cysjava.virtualwallet.service;

import com.alkemy.cysjava.virtualwallet.DTOs.AccountCreationDTO;
import com.alkemy.cysjava.virtualwallet.DTOs.AccountDTO;
import com.alkemy.cysjava.virtualwallet.exceptions.BadRequestException;
import com.alkemy.cysjava.virtualwallet.mappers.AccountMapper;
import com.alkemy.cysjava.virtualwallet.models.Account;
import com.alkemy.cysjava.virtualwallet.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    public AccountService(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    public AccountDTO addAccount(AccountCreationDTO accountCreationDTO) {

        if(accountCreationDTO.getCurrency().equals("ars") || accountCreationDTO.getCurrency().equals("usd")) {
            accountCreationDTO.setCurrency(accountCreationDTO.getCurrency().trim());
        } else {
            throw new BadRequestException("Currency needs to be ARS or USD");
        }

        accountCreationDTO.setUser(accountCreationDTO.getUser());

        Account account = accountMapper.toAccount(accountCreationDTO);

        account.setBalance(0.00);

        if (accountCreationDTO.getCurrency().equalsIgnoreCase("ars")){
            account.setTransactionLimit(300000.00);
        }
        if (accountCreationDTO.getCurrency().equalsIgnoreCase("usd")){
            account.setTransactionLimit(1000.00);
        }

        account.setCreationDate(new Timestamp(System.currentTimeMillis()));

        Account accountCreated = accountRepository.save(account);
        return accountMapper.toAccountDTO(accountCreated);
    }
}
