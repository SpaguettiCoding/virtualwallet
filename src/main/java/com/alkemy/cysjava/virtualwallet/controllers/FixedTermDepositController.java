package com.alkemy.cysjava.virtualwallet.controllers;

import com.alkemy.cysjava.virtualwallet.DTOs.FixedTermDepositCreationDTO;
import com.alkemy.cysjava.virtualwallet.DTOs.FixedTermDepositDTO;
import com.alkemy.cysjava.virtualwallet.models.FixedTermDeposit;
import com.alkemy.cysjava.virtualwallet.service.FixedTermDepositService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fixed-deposits")
public class FixedTermDepositController {

    private final FixedTermDepositService fixedTermDepositService;

    public FixedTermDepositController(FixedTermDepositService fixedTermDepositService){
        this.fixedTermDepositService = fixedTermDepositService;
    }

    @PostMapping("")
    public ResponseEntity<FixedTermDepositDTO> addFixedDeposit(@Valid @RequestBody FixedTermDepositCreationDTO fixedTermDepositCreationDTO){
        FixedTermDepositDTO newFixedTermDepositDTO = fixedTermDepositService.addFixedDeposit(fixedTermDepositCreationDTO);
        return new ResponseEntity<>(newFixedTermDepositDTO, HttpStatus.CREATED);
    }
}
