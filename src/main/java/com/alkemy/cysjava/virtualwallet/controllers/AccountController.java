package com.alkemy.cysjava.virtualwallet.controllers;

import com.alkemy.cysjava.virtualwallet.DTOs.AccountCreationDTO;
import com.alkemy.cysjava.virtualwallet.DTOs.AccountDTO;
import com.alkemy.cysjava.virtualwallet.DTOs.AccountUpdateDTO;
import com.alkemy.cysjava.virtualwallet.DTOs.UserDTO;
import com.alkemy.cysjava.virtualwallet.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Endpoints de Cuentas")
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    @Operation(summary = "Registra una nueva cuenta asociada a un usuario. Retorna el DTO del usuario")
    public ResponseEntity<AccountDTO> addAccount(@RequestBody @Valid AccountCreationDTO accountCreationDTO){
        AccountDTO newAccountDTO = accountService.addAccount(accountCreationDTO);
        return new ResponseEntity<>(newAccountDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Genera una lista de cuentas asociadas a un usuario.")
    public ResponseEntity<List<AccountDTO>> getAccountByUser(@PathVariable Long userId){
        List<AccountDTO> accountsDTO = accountService.findAccountsByUser(userId);
        return new ResponseEntity<>(accountsDTO, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Edita el limite de transaccion de una cuenta.")
    public ResponseEntity<AccountDTO> updateAccount(@PathVariable Long id, @RequestBody @Valid AccountUpdateDTO accountUpdateDTO){
        AccountDTO newAccountDTO = accountService.updateAccount(id, accountUpdateDTO);
        return new ResponseEntity<>(newAccountDTO, HttpStatus.OK);
    }
}
