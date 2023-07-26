package com.alkemy.cysjava.virtualwallet.service;

import com.alkemy.cysjava.virtualwallet.exceptions.BadRequestException;
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

    public TransactionService(TransactionRepository transactionRepository,
                              AccountRepository accountRepository, AccountService accountService, AccountService accountService1, AccountRepository accountRepository1) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService1;
        this.accountRepository = accountRepository1;
    }

    public Account depositToAccount(Transaction transaction){
        Account targetAccount = accountService.findAccountById(transaction.getAccount().getId());
        if (transaction.getAmount() <= targetAccount.getTransactionLimit())
        {
            targetAccount.setBalance(targetAccount.getBalance() + transaction.getAmount());
            accountRepository.save(targetAccount);
            transactionRepository.save(transaction);
            return targetAccount;
        } else throw new BadRequestException("Transaction Over Limit");
    }
}
