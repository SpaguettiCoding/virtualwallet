package com.alkemy.cysjava.virtualwallet.service;

import com.alkemy.cysjava.virtualwallet.DTOs.FixedTermDepositCreationDTO;
import com.alkemy.cysjava.virtualwallet.DTOs.FixedTermDepositDTO;
import com.alkemy.cysjava.virtualwallet.DTOs.FixedTermDepositCreationSimulateDTO;
import com.alkemy.cysjava.virtualwallet.DTOs.FixedTermDepositSimulateDTO;
import com.alkemy.cysjava.virtualwallet.exceptions.BadRequestException;
import com.alkemy.cysjava.virtualwallet.mappers.FixedTermDepositMapper;
import com.alkemy.cysjava.virtualwallet.models.Account;
import com.alkemy.cysjava.virtualwallet.models.FixedTermDeposit;
import com.alkemy.cysjava.virtualwallet.models.Transaction;
import com.alkemy.cysjava.virtualwallet.repositories.FixedTermDepositRepository;
import com.alkemy.cysjava.virtualwallet.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class FixedTermDepositService {

    private final FixedTermDepositRepository fixedTermDepositRepository;
    private final TransactionRepository transactionRepository;
    private final FixedTermDepositMapper fixedTermDepositMapper;
    private final AccountService accountService;

    private final double DEFAULT_INTEREST = 0.5;
    private static final double INTEREST_RATE = 0.005;

    public FixedTermDepositService(FixedTermDepositRepository fixedTermDepositRepository, TransactionRepository transactionRepository, AccountService accountService, FixedTermDepositMapper fixedTermDepositMapper){
        this.fixedTermDepositRepository = fixedTermDepositRepository;
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
        this.fixedTermDepositMapper = fixedTermDepositMapper;
    }
    public FixedTermDepositDTO addFixedDeposit(FixedTermDepositCreationDTO fixedTermDepositCreationDTO) {
        Account account = accountService.findAccountById(fixedTermDepositCreationDTO.getAccountId());

        //Valido que la fecha de cierre no sea menor a 30 días
        if(isClosingDate30daysMinimum(fixedTermDepositCreationDTO.getClosingDate())){
            throw new BadRequestException("Closing date must be a minimum of 30 days.");
        }

        FixedTermDeposit fixedTermDeposit = fixedTermDepositMapper.toFixedTermDeposit(fixedTermDepositCreationDTO);

         //Valido que el balance de la cuenta sea mayor a la cantidad que se quiere invertir en el deposito
        double accountBalance = fixedTermDeposit.getAccount().getBalance();
        double depositAmount = fixedTermDepositCreationDTO.getAmount();

        if(accountBalance - depositAmount < 0){
            throw new BadRequestException("The account amount is insufficient to make the deposit.");
        }else{
            fixedTermDeposit.getAccount().setBalance(accountBalance - depositAmount);
        }

        fixedTermDeposit.setCreationDate(new Timestamp(System.currentTimeMillis()));
        fixedTermDeposit.setClosingDate(fixedTermDepositCreationDTO.getClosingDate());
        fixedTermDeposit.setInterest(DEFAULT_INTEREST);

        Transaction fixedTermtransaction = new Transaction();
        fixedTermtransaction.setAmount(fixedTermDeposit.getAmount());
        fixedTermtransaction.setTransactionType("Fixed-term");
        fixedTermtransaction.setTransactionDate(new Timestamp(System.currentTimeMillis()));
        fixedTermtransaction.setDescription("Fixed term deposit");
        fixedTermtransaction.setAccount(account);

        fixedTermDepositRepository.save(fixedTermDeposit);
        transactionRepository.save(fixedTermtransaction);

        return fixedTermDepositMapper.toFixedTermDepositDTO(fixedTermDeposit);
    }

    public boolean isClosingDate30daysMinimum(Timestamp closingDate) {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        Timestamp minimumClosingDate = Timestamp.from(currentTimestamp.toInstant().plus(30, ChronoUnit.DAYS));

        return closingDate == null  || closingDate.before(minimumClosingDate) ? true : false;

    }

    public FixedTermDepositSimulateDTO simulateFixedTerm(FixedTermDepositCreationSimulateDTO fixedTermDepositCreationSimulateDTO) {

        validateClosingDate(fixedTermDepositCreationSimulateDTO.getClosingDate());
        validateAmount(fixedTermDepositCreationSimulateDTO.getAmount());

        FixedTermDepositSimulateDTO fixedTermDepositDTO = new FixedTermDepositSimulateDTO();
        fixedTermDepositDTO.setAmount(fixedTermDepositCreationSimulateDTO.getAmount());
        fixedTermDepositDTO.setClosingDate(fixedTermDepositCreationSimulateDTO.getClosingDate());
        fixedTermDepositDTO.setCreationDate(Timestamp.from(Instant.now()));

        long days = ChronoUnit.DAYS.between(fixedTermDepositDTO.getCreationDate().toInstant(), fixedTermDepositDTO.getClosingDate().toInstant());

        double interestEarned = calculateInterest(fixedTermDepositDTO.getAmount(), days);
        fixedTermDepositDTO.setInterestEarned(interestEarned);

        double totalAmount = fixedTermDepositDTO.getAmount() + interestEarned;
        fixedTermDepositDTO.setTotalAmount(totalAmount);

        return fixedTermDepositDTO;
    }

    private void validateClosingDate(Timestamp closingDate) {
        Instant currentTimestamp = Instant.now();
        Instant minimumClosingDate = currentTimestamp.plus(30, ChronoUnit.DAYS);

        if (closingDate == null || closingDate.toInstant().isBefore(minimumClosingDate)) {
            throw new BadRequestException("Closing date must be a minimum of 30 days.");
        }
    }

    private void validateAmount(double amount) {
        if (amount <= 0) {
            throw new BadRequestException("Amount must be greater than 0.");
        }
    }

    private double calculateInterest(double amount, long days) {
        BigDecimal interest = BigDecimal.valueOf(amount * INTEREST_RATE * days);
        return interest.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
