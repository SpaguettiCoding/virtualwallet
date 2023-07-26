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
}
