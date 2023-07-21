package com.alkemy.cysjava.virtualwallet.controllers;

import com.alkemy.cysjava.virtualwallet.DTOs.AccountCreationDTO;
import com.alkemy.cysjava.virtualwallet.DTOs.AccountDTO;
import com.alkemy.cysjava.virtualwallet.DTOs.UserDTO;
import com.alkemy.cysjava.virtualwallet.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<AccountDTO> addAccount(@RequestBody @Valid AccountCreationDTO accountCreationDTO){
        AccountDTO newAccountDTO = accountService.addAccount(accountCreationDTO);
        return new ResponseEntity<>(newAccountDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<AccountDTO>> getAccountByUser(@PathVariable Long userId){
        List<AccountDTO> accountsDTO = accountService.findAccountsByUser(userId);
        return new ResponseEntity<>(accountsDTO, HttpStatus.OK);
    }
}
