package com.alkemy.cysjava.virtualwallet.mappers;

import com.alkemy.cysjava.virtualwallet.DTOs.CreditCardCreationDTO;
import com.alkemy.cysjava.virtualwallet.DTOs.CreditCardDTO;
import com.alkemy.cysjava.virtualwallet.exceptions.ResourceNotFoundException;
import com.alkemy.cysjava.virtualwallet.models.Account;
import com.alkemy.cysjava.virtualwallet.models.CreditCard;
import com.alkemy.cysjava.virtualwallet.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CreditCardMapper {

    @Autowired
    private AccountService accountService;

    public CreditCardDTO toCreditCardDTO(CreditCard creditCard) {
        CreditCardDTO dto = new CreditCardDTO();
        dto.setId(creditCard.getId());
        dto.setName(creditCard.getName());
        dto.setAmountAvailable(creditCard.getAmountAvailable());
        dto.setAmount(creditCard.getAmount());

        // Map the Account entity to Account object if the CreditCard has an associated Account
        if (creditCard.getAccount() != null) {
            dto.setAccount(accountService.findOne(creditCard.getAccount().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Account not found with ID: " + creditCard.getAccount().getId())));
        }

        // Set other fields from CreditCard entity if needed
        dto.setCreationDate(creditCard.getCreationDate());
        dto.setClosingDate(creditCard.getClosingDate());

        return dto;
    }

    public CreditCard toCreditCard(CreditCardCreationDTO dto) {
        CreditCard creditCard = new CreditCard();
        creditCard.setAmountAvailable(dto.getAmountAvailable());
        creditCard.setAmount(dto.getAmount());

        if (dto.getAccountId() != null) {
            Account account = accountService.findOne(dto.getAccountId()).orElseThrow(() ->
                    new ResourceNotFoundException("Account not found with ID: " + dto.getAccountId())
            );
            creditCard.setAccount(account);
        }

        creditCard.setCreationDate(LocalDateTime.now()); // Set creationDate here
        return creditCard;
    }

}