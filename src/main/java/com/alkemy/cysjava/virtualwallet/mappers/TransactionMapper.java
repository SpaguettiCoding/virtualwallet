package com.alkemy.cysjava.virtualwallet.mappers;

import com.alkemy.cysjava.virtualwallet.DTOs.TransactionDepositDTO;
import com.alkemy.cysjava.virtualwallet.models.Transaction;
import com.alkemy.cysjava.virtualwallet.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

public class TransactionMapper {
    
    @Autowired
    AccountService accountService;
    
    public TransactionDepositDTO toTransactionDepositDTO(Transaction transaction){
        TransactionDepositDTO transactionDepositDTO = new TransactionDepositDTO();

        transactionDepositDTO.setId(transaction.getId());
        transactionDepositDTO.setAmount(transaction.getAmount());
        transactionDepositDTO.setTransactionType(transaction.getTransactionType());
        transactionDepositDTO.setDescription(transaction.getDescription());
        transactionDepositDTO.setAccountID(transaction.getAccount().getId());
        transactionDepositDTO.setTransactionDate(transaction.getTransactionDate());

        return transactionDepositDTO;
    }

    public Transaction toTransaction(TransactionDepositDTO transactionDepositDTO){
        Transaction transaction = new Transaction();

        transaction.setId(transactionDepositDTO.getId());
        transaction.setAmount(transactionDepositDTO.getAmount());
        transaction.setTransactionType(transactionDepositDTO.getTransactionType());
        transaction.setDescription(transactionDepositDTO.getDescription());
        transaction.setAccount(accountService.findAccountById(transactionDepositDTO.getAccountID()));
        transaction.setTransactionDate(transactionDepositDTO.getTransactionDate());

        return transaction;
    }
}
