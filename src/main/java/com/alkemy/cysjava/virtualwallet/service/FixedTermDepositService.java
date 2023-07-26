package com.alkemy.cysjava.virtualwallet.service;

import com.alkemy.cysjava.virtualwallet.DTOs.FixedTermDepositCreationDTO;
import com.alkemy.cysjava.virtualwallet.DTOs.FixedTermDepositDTO;
import com.alkemy.cysjava.virtualwallet.exceptions.BadRequestException;
import com.alkemy.cysjava.virtualwallet.exceptions.ResourceNotFoundException;
import com.alkemy.cysjava.virtualwallet.mappers.FixedTermDepositMapper;
import com.alkemy.cysjava.virtualwallet.models.Account;
import com.alkemy.cysjava.virtualwallet.models.FixedTermDeposit;
import com.alkemy.cysjava.virtualwallet.repositories.FixedTermDepositRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class FixedTermDepositService {

    private final FixedTermDepositRepository fixedTermDepositRepository;
    private final FixedTermDepositMapper fixedTermDepositMapper;
    private final AccountService accountService;

    private final double DEFAULT_INTEREST = 0.5;

    public FixedTermDepositService(FixedTermDepositRepository fixedTermDepositRepository, AccountService accountService, FixedTermDepositMapper fixedTermDepositMapper){
        this.fixedTermDepositRepository = fixedTermDepositRepository;
        this.accountService = accountService;
        this.fixedTermDepositMapper = fixedTermDepositMapper;
    }
    public FixedTermDepositDTO addFixedDeposit(FixedTermDepositCreationDTO fixedTermDepositCreationDTO) {
        Optional<Account> optionalAccount = accountService.findOne(fixedTermDepositCreationDTO.getAccountId());

        if (!optionalAccount.isPresent()) {
            throw new ResourceNotFoundException("Account not found with ID: " + fixedTermDepositCreationDTO.getAccountId());
        }

        //Valido que la fecha de cierre no sea menor a 30 días
        if(isClosingDate30daysMinimum(fixedTermDepositCreationDTO.getClosingDate())){
            throw new BadRequestException("Closing date must be a minimum of 30 days.");
        }

        FixedTermDeposit fixedTermDeposit = fixedTermDepositMapper.toFixedTermDeposit(fixedTermDepositCreationDTO);

        /* Valido que el balance de la cuenta sea mayor a la cantidad que se quiere invertir en el deposito
        double accountBalance = fixedTermDeposit.getAccount().getBalance();
        double depositAmount = fixedTermDepositCreationDTO.getAmount();

        if(accountBalance - depositAmount < 0){
            throw new BadRequestException("The account amount is insufficient to make the deposit.");
        }else{
            fixedTermDeposit.getAccount().setBalance(accountBalance - depositAmount);
        }
         */
        double accountBalance = fixedTermDeposit.getAccount().getBalance();
        double depositAmount = fixedTermDepositCreationDTO.getAmount();

        fixedTermDeposit.getAccount().setBalance(accountBalance - depositAmount);

        fixedTermDeposit.setCreationDate(new Timestamp(System.currentTimeMillis()));
        fixedTermDeposit.setClosingDate(fixedTermDepositCreationDTO.getClosingDate());
        fixedTermDeposit.setInterest(DEFAULT_INTEREST);
        fixedTermDepositRepository.save(fixedTermDeposit);

        return fixedTermDepositMapper.toFixedTermDepositDTO(fixedTermDeposit);
    }

    public boolean isClosingDate30daysMinimum(Timestamp closingDate) {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        Timestamp minimumClosingDate = Timestamp.from(currentTimestamp.toInstant().plus(30, ChronoUnit.DAYS));

        return closingDate == null  || closingDate.before(minimumClosingDate) ? true : false;

    }
}
