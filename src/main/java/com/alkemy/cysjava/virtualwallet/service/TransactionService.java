package com.alkemy.cysjava.virtualwallet.service;

import com.alkemy.cysjava.virtualwallet.DTOs.AccountDTO;
import com.alkemy.cysjava.virtualwallet.DTOs.TransactionCreationDTO;
import com.alkemy.cysjava.virtualwallet.DTOs.TransactionDTO;
import com.alkemy.cysjava.virtualwallet.DTOs.UserDTO;
import com.alkemy.cysjava.virtualwallet.exceptions.BadRequestException;
import com.alkemy.cysjava.virtualwallet.exceptions.ResourceNotFoundException;
import com.alkemy.cysjava.virtualwallet.mappers.TransactionMapper;
import com.alkemy.cysjava.virtualwallet.models.Account;
import com.alkemy.cysjava.virtualwallet.models.Transaction;
import com.alkemy.cysjava.virtualwallet.models.User;
import com.alkemy.cysjava.virtualwallet.repositories.AccountRepository;
import com.alkemy.cysjava.virtualwallet.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

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

    public List<TransactionDTO> findAllTransactionsDTO() {
        List<Transaction> transaction = transactionRepository.findAll();
        List<TransactionDTO> transactionDTO = new ArrayList<>();
        for (Transaction transaction1 : transaction) {
            if (transaction1 != null) {
                TransactionDTO dto = transactionMapper.toTransactionDTO(transaction1);
                transactionDTO.add(dto);
            }
        }
        return transactionDTO;
    }

    public List<TransactionDTO> findTransactionsByAccount(Long accountId){
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        List<Transaction> transaction = transactionRepository.findTransactionsByAccount(accountId);
        if (transaction.isEmpty()) {
            throw new ResourceNotFoundException("No transactions found for the account");
        }
        List<TransactionDTO> transactionDTO = new ArrayList<>();
        for (Transaction transaction1: transaction) {
            if (transaction1 != null) {
                TransactionDTO dto = transactionMapper.toTransactionDTO(transaction1);
                transactionDTO.add(dto);
            }
        }
        return transactionDTO;
    }

    public Map<String,List<TransactionDTO>> findTransactionsByUser(Long userId) {
        List<Account> accounts = accountRepository.findAccountsByUser(userId);
        Map<String,List<TransactionDTO>> map = new HashMap<>();
        for (Account account1 : accounts) {
            if (account1 != null && account1.getCurrency().equals("ars")) {
                Long accountId = account1.getId();
                Account account = accountRepository.findById(accountId).orElseThrow(() -> new ResourceNotFoundException("Account not found"));
                List<TransactionDTO> transactionsInArs = findTransactionsByAccount(accountId);
                map.put("Transactions in Ars Account",transactionsInArs);
            } else {
                if (account1 != null && account1.getCurrency().equals("usd")) {
                    Long accountId = account1.getId();
                    Account account = accountRepository.findById(accountId).orElseThrow(() -> new ResourceNotFoundException("Account not found"));
                    List<TransactionDTO> transactionsInUsd = findTransactionsByAccount(accountId);
                    map.put("Transactions in Usd Account",transactionsInUsd);
                }
            }
        }
        return map;
    }
}
