package com.alkemy.cysjava.virtualwallet.mappers;

import com.alkemy.cysjava.virtualwallet.DTOs.TransactionCreationDTO;
import com.alkemy.cysjava.virtualwallet.DTOs.TransactionDTO;
import com.alkemy.cysjava.virtualwallet.exceptions.ResourceNotFoundException;
import com.alkemy.cysjava.virtualwallet.models.Transaction;
import com.alkemy.cysjava.virtualwallet.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    @Autowired
    AccountService accountService;

    public TransactionDTO toTransactionDTO(Transaction transaction){
        TransactionDTO dto = new TransactionDTO();

        dto.setAmount(transaction.getAmount());
        dto.setTransactionType(transaction.getTransactionType());
        dto.setDescription(transaction.getDescription());
        dto.setTransactionDate(transaction.getTransactionDate());
        if (transaction.getAccount() != null) {
            dto.setAccount(transaction.getAccount().getId());
        } else {
            dto.setAccount(0L);
        }
        return dto;
    }

    public Transaction toTransaction(TransactionCreationDTO transactionCreationDTO){
        Transaction transaction = new Transaction();
        transaction.setAmount(transactionCreationDTO.getAmount());
        transaction.setAccount(accountService.findOne(transactionCreationDTO.getAccount()).orElseThrow(() -> new ResourceNotFoundException("Account Not Found")));

        return transaction;
    }
}
