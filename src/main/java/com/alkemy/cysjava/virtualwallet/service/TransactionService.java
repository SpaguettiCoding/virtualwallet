package com.alkemy.cysjava.virtualwallet.service;

import com.alkemy.cysjava.virtualwallet.DTOs.AccountDTO;
import com.alkemy.cysjava.virtualwallet.DTOs.TransactionCreationDTO;
import com.alkemy.cysjava.virtualwallet.DTOs.TransactionDTO;
import com.alkemy.cysjava.virtualwallet.DTOs.TransactionSendMoneyDTO;
import com.alkemy.cysjava.virtualwallet.DTOs.UserDTO;
import com.alkemy.cysjava.virtualwallet.exceptions.BadRequestException;
import com.alkemy.cysjava.virtualwallet.exceptions.ResourceNotFoundException;
import com.alkemy.cysjava.virtualwallet.mappers.TransactionMapper;
import com.alkemy.cysjava.virtualwallet.models.Account;
import com.alkemy.cysjava.virtualwallet.models.Transaction;
import com.alkemy.cysjava.virtualwallet.models.User;
import com.alkemy.cysjava.virtualwallet.repositories.AccountRepository;
import com.alkemy.cysjava.virtualwallet.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final TransactionMapper transactionMapper;

    public TransactionService(TransactionRepository transactionRepository,
                              AccountRepository accountRepository, AccountService accountService, AccountService accountService1, AccountRepository accountRepository1, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService1;
        this.accountRepository = accountRepository1;
        this.transactionMapper = transactionMapper;
    }

    public TransactionDTO depositToAccount(TransactionCreationDTO transactionCreationDTO){

        if (transactionCreationDTO.getAmount() > 0) {
            transactionCreationDTO.setAmount(transactionCreationDTO.getAmount());
        } else {
            throw new BadRequestException("amount must be greater than 0");
        }

        transactionCreationDTO.setAccount(transactionCreationDTO.getAccount());

        Transaction transaction = transactionMapper.toTransaction(transactionCreationDTO);
        transaction.setTransactionType("deposit");
        transaction.setDescription("deposit received");
        transaction.setTransactionDate(new Timestamp(System.currentTimeMillis()));

        Optional<Account> optionalAccount = accountService.findOne(transactionCreationDTO.getAccount());
        if(!optionalAccount.isPresent()){
            throw new ResourceNotFoundException("Account not found");
        }
        Account account = optionalAccount.get();

        account.setBalance(account.getBalance() + transactionCreationDTO.getAmount());

        Transaction transactionCreated = transactionRepository.save(transaction);

        return transactionMapper.toTransactionDTO(transactionCreated);
    }

  
    public List<TransactionDTO> sendArs(TransactionSendMoneyDTO transactionSendMoneyDTO) {
        Account account = accountService.findAccountById(transactionSendMoneyDTO.getAccount());
        //valido que la cuenta que trata de enviar dinero sea en pesos
        if(!account.getCurrency().equals("ars")){
            throw new BadRequestException("It is not possible to perform this operation, the account must be in pesos.");
        }
        Account targetAccount = accountService.findAccountById(transactionSendMoneyDTO.getTargetAccount());
        //valido que la cuenta destinataria tambien sea en pesos
        if(!targetAccount.getCurrency().equals("ars")){
            throw new BadRequestException("It is not possible to perform this operation, the target account must be in pesos.");
        }
        //invoco metodo para validar el payment
        validatePaymentTransaction(account, transactionSendMoneyDTO.getAmount());

        //invoco metodo para setear el balance de ambas cuentas
        performMoneyTransfer(account, targetAccount, transactionSendMoneyDTO.getAmount());

        //invoco metodo para la creacion de la transaccion segun su tipo de movimiento
        Transaction paymentTransaction = createTransaction(account, transactionSendMoneyDTO.getAmount(), "payment");
        Transaction incomeTransaction = createTransaction(targetAccount, transactionSendMoneyDTO.getAmount(), "income");

        //guardo en base de datos las modificaciones del balance de la cuenta
        accountRepository.save(account);
        accountRepository.save(targetAccount);

        //guardo en base de datos las transferencias
        transactionRepository.save(paymentTransaction);
        transactionRepository.save(incomeTransaction);

        //retorno la lista de transacciones mapeadas a dto
        return Arrays.asList(transactionMapper.toTransactionDTO(paymentTransaction),
                transactionMapper.toTransactionDTO(incomeTransaction));
    }

    //metodo validacion payment
    private void validatePaymentTransaction(Account account, double amount) {
        //valido que el balance de la cuenta que quiere transferir dinero sea suficiente
        //valido que el limite de transaccion no sea excedido
        if (account.getBalance() < amount) {
            throw new BadRequestException("Insufficient balance, transfer could not be made");
        } else if (account.getTransactionLimit() < amount) {
            throw new BadRequestException("You have exceeded the transaction limit");
        }
    }

    //metodo para setear balance de ambas cuentas
    private void performMoneyTransfer(Account account, Account targetAccount, double amount) {
        account.setBalance(account.getBalance() - amount);
        targetAccount.setBalance(targetAccount.getBalance() + amount);
    }

    //metodo que crea la transaccion
    private Transaction createTransaction(Account account, double amount, String transactionType) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTransactionType(transactionType);
        transaction.setTransactionDate(new Timestamp(System.currentTimeMillis()));
        transaction.setAccount(account);

        //asignacion del tipo de transaccion
        if ("payment".equals(transactionType) && account.getCurrency().equals("ars")) {
            transaction.setDescription("Transfer of money sent in pesos");
        } else if ("income".equals(transactionType) && account.getCurrency().equals("ars")) {
            transaction.setDescription("Transfer of money received in pesos");
        }
        if ("payment".equals(transactionType) && account.getCurrency().equals("usd")) {
            transaction.setDescription("Transfer of money sent in usd");
        } else if ("income".equals(transactionType) && account.getCurrency().equals("usd")) {
            transaction.setDescription("Transfer of money received in usd");
        }

        return transaction;
    }

  
    public List<TransactionDTO> findAllTransactionsDTO() {
        List<Transaction> transaction = transactionRepository.findAll();
        List<TransactionDTO> transactionDTO = new ArrayList<>();
        for (Transaction transaction1 : transaction) {
            if (transaction1 != null) {
                TransactionDTO dto = transactionMapper.toTransactionDTO(transaction1);
                transactionDTO.add(dto);
            }
        }
        return transactionDTO;
    }

    public List<TransactionDTO> findTransactionsByAccount(Long accountId){
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        List<Transaction> transaction = transactionRepository.findTransactionsByAccount(accountId);
        if (transaction.isEmpty()) {
            throw new ResourceNotFoundException("No transactions found for the account");
        }
        List<TransactionDTO> transactionDTO = new ArrayList<>();
        for (Transaction transaction1: transaction) {
            if (transaction1 != null) {
                TransactionDTO dto = transactionMapper.toTransactionDTO(transaction1);
                transactionDTO.add(dto);
            }
        }
        return transactionDTO;
    }

    public Map<String,List<TransactionDTO>> findTransactionsByUser(Long userId) {
        List<Account> accounts = accountRepository.findAccountsByUser(userId);
        Map<String,List<TransactionDTO>> map = new HashMap<>();
        for (Account account1 : accounts) {
            if (account1 != null && account1.getCurrency().equals("ars")) {
                Long accountId = account1.getId();
                Account account = accountRepository.findById(accountId).orElseThrow(() -> new ResourceNotFoundException("Account not found"));
                List<TransactionDTO> transactionsInArs = findTransactionsByAccount(accountId);
                map.put("Transactions in Ars Account",transactionsInArs);
            } else {
                if (account1 != null && account1.getCurrency().equals("usd")) {
                    Long accountId = account1.getId();
                    Account account = accountRepository.findById(accountId).orElseThrow(() -> new ResourceNotFoundException("Account not found"));
                    List<TransactionDTO> transactionsInUsd = findTransactionsByAccount(accountId);
                    map.put("Transactions in Usd Account",transactionsInUsd);
                }
            }
        }
        return map;
    }

    public List<TransactionDTO> sendUsd(TransactionSendMoneyDTO transactionSendMoneyDTO) {
        Account account = accountService.findAccountById(transactionSendMoneyDTO.getAccount());
        //valido que la cuenta que trata de enviar dinero sea en dolares
        if(!account.getCurrency().equals("usd")){
            throw new BadRequestException("It is not possible to perform this operation, the account must be in usd.");
        }
        Account targetAccount = accountService.findAccountById(transactionSendMoneyDTO.getTargetAccount());
        //valido que la cuenta destinataria tambien sea en dolares
        if(!targetAccount.getCurrency().equals("usd")){
            throw new BadRequestException("It is not possible to perform this operation, the target account must be in usd.");
        }

        //invoco metodo para validar el payment
        validatePaymentTransaction(account, transactionSendMoneyDTO.getAmount());

        //invoco metodo para setear el balance de ambas cuentas
        performMoneyTransfer(account, targetAccount, transactionSendMoneyDTO.getAmount());

        //invoco metodo para la creacion de la transaccion segun su tipo de movimiento
        Transaction paymentTransaction = createTransaction(account, transactionSendMoneyDTO.getAmount(), "payment");
        Transaction incomeTransaction = createTransaction(targetAccount, transactionSendMoneyDTO.getAmount(), "income");

        //guardo en base de datos las modificaciones del balance de la cuenta
        accountRepository.save(account);
        accountRepository.save(targetAccount);

        //guardo en base de datos las transferencias
        transactionRepository.save(paymentTransaction);
        transactionRepository.save(incomeTransaction);

        //retorno la lista de transacciones mapeadas a dto
        return Arrays.asList(transactionMapper.toTransactionDTO(paymentTransaction),
                transactionMapper.toTransactionDTO(incomeTransaction));
    }
}
