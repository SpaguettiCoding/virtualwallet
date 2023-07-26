package com.alkemy.cysjava.virtualwallet.controllers;

import com.alkemy.cysjava.virtualwallet.DTOs.TransactionDepositDTO;
import com.alkemy.cysjava.virtualwallet.exceptions.BadRequestException;
import com.alkemy.cysjava.virtualwallet.mappers.TransactionMapper;
import com.alkemy.cysjava.virtualwallet.models.Account;
import com.alkemy.cysjava.virtualwallet.models.Transaction;
import com.alkemy.cysjava.virtualwallet.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    TransactionMapper transactionMapper;

    @PostMapping("/deposit")
    public ResponseEntity<TransactionDepositDTO> depositToAccount(@RequestBody @Valid TransactionDepositDTO transactionDepositDTO){
        try {
            if (transactionDepositDTO.getAmount() >= 0) {
                Transaction transaction = transactionMapper.toTransaction(transactionService.depositToAccount(transactionDepositDTO));
                return new ResponseEntity<>(transactionDepositDTO, HttpStatus.ACCEPTED);
            } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch(BadRequestException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
