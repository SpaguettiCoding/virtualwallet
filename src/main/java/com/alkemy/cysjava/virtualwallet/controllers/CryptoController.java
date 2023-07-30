package com.alkemy.cysjava.virtualwallet.controllers;

import com.alkemy.cysjava.virtualwallet.DTOs.*;
import com.alkemy.cysjava.virtualwallet.exceptions.ResourceNotFoundException;
import com.alkemy.cysjava.virtualwallet.mappers.CryptoMapper;
import com.alkemy.cysjava.virtualwallet.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/crypto")
public class CryptoController {

    @Autowired
    private CryptoService cryptoService;

    @PostMapping("/register")
    public ResponseEntity<CryptoDTO> addCrypto(@RequestBody @Valid CryptoCreationDTO cryptoCreationDTO){
        CryptoDTO newCryptoDTO = cryptoService.addCrypto(cryptoCreationDTO);
        return new ResponseEntity<>(newCryptoDTO, HttpStatus.CREATED);
    }
}

