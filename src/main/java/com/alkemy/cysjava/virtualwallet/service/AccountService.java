package com.alkemy.cysjava.virtualwallet.service;

import com.alkemy.cysjava.virtualwallet.DTOs.AccountCreationDTO;
import com.alkemy.cysjava.virtualwallet.DTOs.AccountDTO;
import com.alkemy.cysjava.virtualwallet.DTOs.AccountUpdateDTO;
import com.alkemy.cysjava.virtualwallet.exceptions.BadRequestException;
import com.alkemy.cysjava.virtualwallet.exceptions.ResourceNotFoundException;
import com.alkemy.cysjava.virtualwallet.mappers.AccountMapper;
import com.alkemy.cysjava.virtualwallet.models.Account;
import com.alkemy.cysjava.virtualwallet.models.Transaction;
import com.alkemy.cysjava.virtualwallet.models.User;
import com.alkemy.cysjava.virtualwallet.repositories.AccountRepository;
import com.alkemy.cysjava.virtualwallet.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    private final UserRepository userRepository;
    private final AccountMapper accountMapper;

    public AccountService(AccountRepository accountRepository, UserRepository userRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.accountMapper = accountMapper;
    }

    public AccountDTO addAccount(AccountCreationDTO accountCreationDTO) {

        if(accountCreationDTO.getCurrency().equals("ars") || accountCreationDTO.getCurrency().equals("usd")) {
            accountCreationDTO.setCurrency(accountCreationDTO.getCurrency().trim());
        } else {
            throw new BadRequestException("Currency must be ARS or USD");
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

    public List<AccountDTO> findAccountsByUser(Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<Account> account = accountRepository.findAccountsByUser(userId);
        if (account.isEmpty()) {
            throw new ResourceNotFoundException("No accounts found for the user");
        }
        List<AccountDTO> accountDTO = new ArrayList<>();
        for (Account account1: account) {
            if (account1 != null) {
                AccountDTO dto = accountMapper.toAccountDTO(account1);
                accountDTO.add(dto);
            }
        }
        return accountDTO;
    }

    /*
    public Optional<Account> findOne(Long id){
        return accountRepository.findById(id);
    }
    */

    public AccountDTO updateAccount(Long id, AccountUpdateDTO accountUpdateDTO) {

        Optional<Account> OptionalAccount = accountRepository.findById(id);

        if(!OptionalAccount.isPresent()){
            throw new ResourceNotFoundException("Account not found");
        }

        Account account = OptionalAccount.get();

            accountUpdateDTO.setTransactionLimit(accountUpdateDTO.getTransactionLimit());
            account.setTransactionLimit(accountUpdateDTO.getTransactionLimit());
            account.setUpdateDate(new Timestamp(System.currentTimeMillis()));
        accountRepository.save(account);
        return accountMapper.toAccountDTO(account);
    }

    public Optional<Account> findOne(Long id){
       return accountRepository.findById(id);
    }

    public Account findAccountById(Long id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);

        if (!optionalAccount.isPresent()) {
            throw new ResourceNotFoundException("Account not found");
        }
        return optionalAccount.get();
    }
}
