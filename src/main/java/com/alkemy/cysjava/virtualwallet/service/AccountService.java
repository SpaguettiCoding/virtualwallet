package com.alkemy.cysjava.virtualwallet.service;

import com.alkemy.cysjava.virtualwallet.DTOs.AccountCreationDTO;
import com.alkemy.cysjava.virtualwallet.DTOs.AccountDTO;
import com.alkemy.cysjava.virtualwallet.DTOs.AccountUpdateDTO;
import com.alkemy.cysjava.virtualwallet.exceptions.BadRequestException;
import com.alkemy.cysjava.virtualwallet.exceptions.ResourceNotFoundException;
import com.alkemy.cysjava.virtualwallet.mappers.AccountMapper;
import com.alkemy.cysjava.virtualwallet.models.Account;
import com.alkemy.cysjava.virtualwallet.models.User;
import com.alkemy.cysjava.virtualwallet.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountService(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    public AccountDTO addAccount(AccountCreationDTO accountCreationDTO) {
        validateAndTrimCurrency(accountCreationDTO);

        Account account = createAccountFromDTO(accountCreationDTO);
        account.setBalance(0.00);
        setTransactionLimit(accountCreationDTO.getCurrency(), account);
        account.setCreationDate(new Timestamp(System.currentTimeMillis()));

        Account accountCreated = accountRepository.save(account);
        return accountMapper.toAccountDTO(accountCreated);
    }

    private void validateAndTrimCurrency(AccountCreationDTO accountCreationDTO) {
        String currency = accountCreationDTO.getCurrency();
        if (!currency.equalsIgnoreCase("ars") && !currency.equalsIgnoreCase("usd")) {
            throw new BadRequestException("Currency must be ARS or USD");
        }
        accountCreationDTO.setCurrency(currency.trim());
    }

    private Account createAccountFromDTO(AccountCreationDTO accountCreationDTO) {
        accountCreationDTO.setUser(accountCreationDTO.getUser());
        return accountMapper.toAccount(accountCreationDTO);
    }

    private void setTransactionLimit(String currency, Account account) {
        double transactionLimit;

        if (currency.equalsIgnoreCase("ars")) {
            transactionLimit = 300000.00;
        } else if (currency.equalsIgnoreCase("usd")) {
            transactionLimit = 1000.00;
        } else {
            return; // Si no es "ars" ni "usd", el límite de transacción permanecerá sin cambios.
                    //De igual forma esta controlado en el metodo validateAndTrimCurrency.
        }

        account.setTransactionLimit(transactionLimit);
    }

    public List<AccountDTO> findAccountsByUser(Long userId){
        List<Account> account = accountRepository.findAccountsByUser(userId);
        if (account.isEmpty()) {
            throw new ResourceNotFoundException("No accounts found for the user");
        }
        return account.stream()
                .filter(Objects::nonNull)
                .map(accountMapper::toAccountDTO)
                .collect(Collectors.toList());
    }

    public AccountDTO updateAccount(Long id, AccountUpdateDTO accountUpdateDTO) {
        Account account = findAccountById(id);
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
