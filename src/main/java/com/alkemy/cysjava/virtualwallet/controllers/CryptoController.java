package com.alkemy.cysjava.virtualwallet.controllers;

import com.alkemy.cysjava.virtualwallet.DTOs.CryptoCreationDTO;
import com.alkemy.cysjava.virtualwallet.mappers.CryptoMapper;
import com.alkemy.cysjava.virtualwallet.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CryptoController {
    private final CryptoService cryptoService;
    private final CryptoMapper cryptoMapper;

    public CryptoController(CryptoService cryptoService, CryptoMapper cryptoMapper) {
        this.cryptoService = cryptoService;
        this.cryptoMapper = cryptoMapper;
    }

    @PostMapping("/crypto/register")
    public ResponseEntity<CryptoCreationDTO> addCrypto(@Validated @RequestBody CryptoCreationDTO cryptoCreationDTO) {
        CryptoCreationDTO newCryptoDTO = cryptoService.addCrypto(cryptoCreationDTO);
        return new ResponseEntity<>(newCryptoDTO, HttpStatus.CREATED);
    }
}

