package com.alkemy.cysjava.virtualwallet.controllers;

import com.alkemy.cysjava.virtualwallet.DTOs.AccountDTO;
import com.alkemy.cysjava.virtualwallet.DTOs.TransactionCreationDTO;
import com.alkemy.cysjava.virtualwallet.DTOs.TransactionDTO;
import com.alkemy.cysjava.virtualwallet.DTOs.TransactionSendMoneyDTO;
import com.alkemy.cysjava.virtualwallet.exceptions.BadRequestException;
import com.alkemy.cysjava.virtualwallet.models.Account;
import com.alkemy.cysjava.virtualwallet.models.Transaction;
import com.alkemy.cysjava.virtualwallet.service.AccountService;
import com.alkemy.cysjava.virtualwallet.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@Tag(name = "Endpoints de Transacciones")
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountService accountService;

    @PostMapping("/deposit")
    @Operation(summary = "Registra una nueva transaccion de deposito en una cuenta. Modifica el balance.")
    public ResponseEntity<TransactionDTO> depositToAccount(@RequestBody @Valid TransactionCreationDTO transactionCreationDTO){
        TransactionDTO newTransactionDTO = transactionService.depositToAccount(transactionCreationDTO);
        return new ResponseEntity<>(newTransactionDTO, HttpStatus.OK);
    }


    @PostMapping("/sendars")
    @Operation(summary = "Registra nueva transaccion de envio de pesos entre cuentas. Modifica el balance de ambas cuentas.")
    public ResponseEntity<List<TransactionDTO>> sendArs(@RequestBody @Valid TransactionSendMoneyDTO transactionSendMoneyDTO){
        List<TransactionDTO> newTransactionDTO = transactionService.sendArs(transactionSendMoneyDTO);
        return new ResponseEntity<>(newTransactionDTO, HttpStatus.OK);
    }

    @PostMapping("/sendusd")
    @Operation(summary = "Registra nueva transaccion de envio de dolares entre cuentas. Modifica el balance de ambas cuentas.")
    public ResponseEntity<List<TransactionDTO>> sendUsd(@RequestBody @Valid TransactionSendMoneyDTO transactionSendMoneyDTO){
        List<TransactionDTO> newTransactionDTO = transactionService.sendUsd(transactionSendMoneyDTO);
        return new ResponseEntity<>(newTransactionDTO, HttpStatus.OK);
    }
  
    @GetMapping("/{userId}")
    @Operation(summary = "Genera una lista de transacciones asociadas a un usuario.")
    public ResponseEntity<Map<String,List<TransactionDTO>>> listTransactions(@PathVariable Long userId){
        Map<String,List<TransactionDTO>> transactionsByUser = transactionService.findTransactionsByUser(userId);
        return new ResponseEntity<>(transactionsByUser, HttpStatus.OK);
    }
}
