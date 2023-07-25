package com.alkemy.cysjava.virtualwallet.service;

import com.alkemy.cysjava.virtualwallet.DTOs.CreditCardCreationDTO;
import com.alkemy.cysjava.virtualwallet.mappers.CreditCardMapper;
import com.alkemy.cysjava.virtualwallet.models.Account;
import com.alkemy.cysjava.virtualwallet.models.CreditCard;
import com.alkemy.cysjava.virtualwallet.repositories.AccountRepository;
import com.alkemy.cysjava.virtualwallet.repositories.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CreditCardService {


    private final CreditCardMapper creditCardMapper;
    private final CreditCardRepository creditCardRepository;
    private final AccountRepository accountRepository;


    @Autowired
    public CreditCardService(CreditCardMapper creditCardMapper, CreditCardRepository creditCardRepository, AccountRepository accountRepository) {
        this.creditCardRepository = creditCardRepository;
        this.creditCardMapper = creditCardMapper;
        this.accountRepository = accountRepository;
    }

    public CreditCard createCreditCard(CreditCardCreationDTO creditCardDTO) {
        CreditCard creditCard = creditCardMapper.toCreditCard(creditCardDTO);
        creditCard.setAmount(0);
        setClosingDateOneMonthAfterCreation(creditCard);
        Account account = accountRepository.findById(creditCardDTO.getAccountId()).orElse(null);
        if (account != null) {
            String fullName = account.getUser().getFirstName() + " " + account.getUser().getLastName();
            creditCard.setName(fullName);
            String amountByCurrency = account.getCurrency();
            if (amountByCurrency.equals("usd")) {
                creditCard.setAmountAvailable(1000);
            }else {
                creditCard.setAmountAvailable(350000);
            }
        }

        return creditCardRepository.save(creditCard);
    }

    private void setClosingDateOneMonthAfterCreation(CreditCard creditCard) {
        if (creditCard.getCreationDate() != null) {
            LocalDateTime currentDate = LocalDateTime.now();
            LocalDateTime creationDate = creditCard.getCreationDate();
            LocalDateTime closingDate = creationDate.plusMonths(1);

            if (currentDate.isAfter(closingDate)) {
                closingDate = currentDate.plusMonths(1);
            }
            creditCard.setClosingDate(closingDate);
        }
    }




}
