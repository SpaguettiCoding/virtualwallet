package com.alkemy.cysjava.virtualwallet.mappers;

import com.alkemy.cysjava.virtualwallet.DTOs.FixedTermDepositCreationDTO;
import com.alkemy.cysjava.virtualwallet.DTOs.FixedTermDepositDTO;
import com.alkemy.cysjava.virtualwallet.exceptions.ResourceNotFoundException;
import com.alkemy.cysjava.virtualwallet.models.FixedTermDeposit;
import com.alkemy.cysjava.virtualwallet.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FixedTermDepositMapper {

    @Autowired
    AccountService accountService;

    public FixedTermDepositDTO toFixedTermDepositDTO(FixedTermDeposit fixedTermDeposit){
        FixedTermDepositDTO fixedTermDepositDTO = new FixedTermDepositDTO();

        fixedTermDepositDTO.setId(fixedTermDeposit.getId());
        fixedTermDepositDTO.setAmount(fixedTermDeposit.getAmount());
        fixedTermDepositDTO.setInterest(fixedTermDeposit.getInterest());
        fixedTermDepositDTO.setCreationDate(fixedTermDeposit.getCreationDate());
        fixedTermDepositDTO.setClosingDate(fixedTermDeposit.getClosingDate());
        fixedTermDepositDTO.setAccountId(fixedTermDeposit.getAccount().getId());
        fixedTermDepositDTO.setAccountBalance(fixedTermDeposit.getAccount().getBalance());

        return fixedTermDepositDTO;
    }

    public FixedTermDeposit toFixedTermDeposit(FixedTermDepositCreationDTO fixedTermDepositCreationDTO){
        FixedTermDeposit fixedTermDeposit = new FixedTermDeposit();

        fixedTermDeposit.setAmount(fixedTermDepositCreationDTO.getAmount());
        fixedTermDeposit.setClosingDate(fixedTermDepositCreationDTO.getClosingDate());
        fixedTermDeposit.setAccount(accountService.findOne(fixedTermDepositCreationDTO.getAccountId()).orElseThrow(() -> new ResourceNotFoundException("Account not found")));

        return fixedTermDeposit;
    }
}
