package com.alkemy.cysjava.virtualwallet.mappers;

import com.alkemy.cysjava.virtualwallet.DTOs.CryptoCreationDTO;
import com.alkemy.cysjava.virtualwallet.DTOs.CryptoDTO;
import com.alkemy.cysjava.virtualwallet.exceptions.ResourceNotFoundException;
import com.alkemy.cysjava.virtualwallet.models.Crypto;
import com.alkemy.cysjava.virtualwallet.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Objects;

@Component
public class CryptoMapper {
    @Autowired
    AccountService accountService;

    public CryptoDTO toCryptoDTO(Crypto crypto){
        CryptoDTO dto = new CryptoDTO();
        dto.setId(crypto.getId());
        dto.setName(crypto.getName());
        dto.setAmount(crypto.getAmount());
        if(crypto.getAccount() != null){
            dto.setAccount(crypto.getAccount().getId());
        }else{
            dto.setAccount(0L);
        }
        return dto;
    }

    public Crypto toCrypto(CryptoCreationDTO dto){
        Crypto crypto = new Crypto();
        crypto.setName(dto.getName());
        crypto.setAmount(dto.getAmount());
        crypto.setAccount(accountService.findAccountById(dto.getAccount()).orElseThrow(() -> new ResourceNotFoundException("Account not found")));
        return crypto;
    }
}
