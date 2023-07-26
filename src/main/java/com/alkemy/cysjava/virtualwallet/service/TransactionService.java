package com.alkemy.cysjava.virtualwallet.service;

import com.alkemy.cysjava.virtualwallet.DTOs.TransactionCreationDTO;
import com.alkemy.cysjava.virtualwallet.DTOs.TransactionDTO;
import com.alkemy.cysjava.virtualwallet.exceptions.BadRequestException;
import com.alkemy.cysjava.virtualwallet.exceptions.ResourceNotFoundException;
import com.alkemy.cysjava.virtualwallet.mappers.TransactionMapper;
import com.alkemy.cysjava.virtualwallet.models.Account;
import com.alkemy.cysjava.virtualwallet.models.Transaction;
import com.alkemy.cysjava.virtualwallet.repositories.AccountRepository;
import com.alkemy.cysjava.virtualwallet.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountService accountService;
    private final AccountRepository accountRepository;

    private final TransactionMapper transactionMapper;

    public TransactionService(TransactionRepository transactionRepository,
                              AccountRepository accountRepository, AccountService accountService, AccountService accountService1, AccountRepository accountRepository1, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService1;
        this.accountRepository = accountRepository1;
        this.transactionMapper = transactionMapper;
    }

    public TransactionDTO depositToAccount(TransactionCreationDTO transactionCreationDTO){

        if (transactionCreationDTO.getAmount() > 0) {
            transactionCreationDTO.setAmount(transactionCreationDTO.getAmount());
        } else {
            throw new BadRequestException("amount must be greater than 0");
        }

        transactionCreationDTO.setAccount(transactionCreationDTO.getAccount());

        Transaction transaction = transactionMapper.toTransaction(transactionCreationDTO);
        transaction.setTransactionType("deposit");
        transaction.setDescription("Deposito recibido");
        transaction.setTransactionDate(new Timestamp(System.currentTimeMillis()));

        Optional<Account> optionalAccount = accountService.findOne(transactionCreationDTO.getAccount());
        if(!optionalAccount.isPresent()){
            throw new ResourceNotFoundException("Account not found");
        }
        Account account = optionalAccount.get();

        account.setBalance(account.getBalance() + transactionCreationDTO.getAmount());

        Transaction transactionCreated = transactionRepository.save(transaction);

        return transactionMapper.toTransactionDTO(transactionCreated);
    }
}
