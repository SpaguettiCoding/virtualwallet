package com.alkemy.cysjava.virtualwallet.service;

import com.alkemy.cysjava.virtualwallet.DTOs.*;
import com.alkemy.cysjava.virtualwallet.exceptions.BadRequestException;
import com.alkemy.cysjava.virtualwallet.exceptions.ResourceNotFoundException;
import com.alkemy.cysjava.virtualwallet.mappers.CryptoMapper;
import com.alkemy.cysjava.virtualwallet.models.Account;
import com.alkemy.cysjava.virtualwallet.models.Crypto;
import com.alkemy.cysjava.virtualwallet.models.User;
import com.alkemy.cysjava.virtualwallet.repositories.AccountRepository;
import com.alkemy.cysjava.virtualwallet.repositories.CryptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class CryptoService {
    private final CryptoRepository cryptoRepository;
    private final CryptoMapper cryptoMapper;
    private final AccountRepository accountRepository;
    public CryptoService(CryptoRepository cryptoRepository, CryptoMapper cryptoMapper, AccountRepository accountRepository) {
        this.cryptoMapper = cryptoMapper;
        this.cryptoRepository = cryptoRepository;
        this.accountRepository = accountRepository;
    }

    public CryptoDTO addCrypto(CryptoCreationDTO cryptoCreationDTO) {
        Crypto crypto = cryptoMapper.toCrypto(cryptoCreationDTO);

        cryptoCreationDTO.setName(cryptoCreationDTO.getName().trim());
        cryptoCreationDTO.setAmount(cryptoCreationDTO.getAmount());
        cryptoCreationDTO.setAccount(cryptoCreationDTO.getAccount());
        crypto.setCreationDate(new Timestamp(System.currentTimeMillis()));

        Crypto createdCrypto = cryptoRepository.save(crypto);
        return cryptoMapper.toCryptoDTO(createdCrypto);
    }

    public CryptoDTO findCryptoById(Long id) {
        Optional<Crypto> optionalCrypto = cryptoRepository.findById(id);

        if (!optionalCrypto.isPresent()) {
            throw new ResourceNotFoundException("Crypto not found");
        }
        Crypto crypto = optionalCrypto.get();
        return cryptoMapper.toCryptoDTO(crypto);
    }

    public CryptoDTO updateCrypto(Long id, CryptoUpdateDTO cryptoUpdateDTO) {

        Optional<Crypto> OptionalCrypto = cryptoRepository.findById(id);

        if(!OptionalCrypto.isPresent()){
            throw new ResourceNotFoundException("Crypto not found");
        }

        Crypto crypto = OptionalCrypto.get();

        cryptoUpdateDTO.setAmount(cryptoUpdateDTO.getAmount());
        crypto.setAmount(cryptoUpdateDTO.getAmount());
        cryptoRepository.save(crypto);
        return cryptoMapper.toCryptoDTO(crypto);
    }
}
