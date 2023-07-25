package com.alkemy.cysjava.virtualwallet.mappers;

import com.alkemy.cysjava.virtualwallet.DTOs.CreditCardCreationDTO;
import com.alkemy.cysjava.virtualwallet.exceptions.ResourceNotFoundException;
import com.alkemy.cysjava.virtualwallet.models.Account;
import com.alkemy.cysjava.virtualwallet.models.CreditCard;
import com.alkemy.cysjava.virtualwallet.service.AccountService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CreditCardMapper {

    @Autowired
    private AccountService accountService;

    @PersistenceContext
    private EntityManager entityManager;

    public CreditCardCreationDTO toCreditCardDTO(CreditCard creditCard) {
        CreditCardCreationDTO dto = new CreditCardCreationDTO();
        dto.setAmountAvailable(creditCard.getAmountAvailable());
        dto.setAmount(creditCard.getAmount());
        dto.setAccountId(creditCard.getAccount() != null ? creditCard.getAccount().getId() : null);
        return dto;
    }

    public CreditCard toCreditCard(CreditCardCreationDTO dto) {
        CreditCard creditCard = new CreditCard();
        creditCard.setAmountAvailable(dto.getAmountAvailable());
        creditCard.setAmount(dto.getAmount());
        if (dto.getAccountId() != null) {
            Account account = entityManager.find(Account.class, dto.getAccountId());
            if (account == null) {
                throw new ResourceNotFoundException("Account not found with ID: " + dto.getAccountId());
            }
            creditCard.setAccount(account);
        }
        creditCard.setCreationDate(LocalDateTime.now()); // Set creationDate here
        return creditCard;

    }

}
