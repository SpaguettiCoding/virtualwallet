package com.alkemy.cysjava.virtualwallet.mappers;

import com.alkemy.cysjava.virtualwallet.DTOs.AccountCreationDTO;
import com.alkemy.cysjava.virtualwallet.DTOs.AccountDTO;
import com.alkemy.cysjava.virtualwallet.exceptions.ResourceNotFoundException;
import com.alkemy.cysjava.virtualwallet.models.Account;
import com.alkemy.cysjava.virtualwallet.service.AccountService;
import com.alkemy.cysjava.virtualwallet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    @Autowired
    private UserService userService;

    public AccountDTO toAccountDTO(Account account){
        AccountDTO dto = new AccountDTO();
        dto.setCurrency(account.getCurrency());
        dto.setBalance(account.getBalance());
        dto.setTransactionLimit(account.getTransactionLimit());
        if (account.getUser() != null) {
            dto.setUser(account.getUser().getId());
        } else {
            dto.setUser(0L);
        }

        return dto;
    }

    public Account toAccount(AccountCreationDTO dto) {
        Account account = new Account();
        account.setCurrency(dto.getCurrency());
        account.setUser(userService.findById(dto.getUser()).orElseThrow(() -> new ResourceNotFoundException("User Not Found")));
        return account;
    }
}
