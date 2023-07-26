package com.alkemy.cysjava.virtualwallet.service;

import com.alkemy.cysjava.virtualwallet.DTOs.TransactionDepositDTO;
import com.alkemy.cysjava.virtualwallet.exceptions.BadRequestException;
import com.alkemy.cysjava.virtualwallet.mappers.TransactionMapper;
import com.alkemy.cysjava.virtualwallet.models.Account;
import com.alkemy.cysjava.virtualwallet.models.Transaction;
import com.alkemy.cysjava.virtualwallet.repositories.AccountRepository;
import com.alkemy.cysjava.virtualwallet.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

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

    public TransactionDepositDTO depositToAccount(TransactionDepositDTO transactionDepositDTO){
        Account targetAccount = accountService.findAccountById(transactionDepositDTO.getAccountID());
        if (transactionDepositDTO.getAmount() <= targetAccount.getTransactionLimit())
        {
            targetAccount.setBalance(targetAccount.getBalance() + transactionDepositDTO.getAmount());
            accountRepository.save(targetAccount);
            Transaction transaction = transactionMapper.toTransaction(transactionDepositDTO);
            transactionRepository.save(transaction);
            return transactionDepositDTO;
        } else throw new BadRequestException("Transaction Over Limit");
    }
}
