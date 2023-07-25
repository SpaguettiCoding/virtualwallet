package com.alkemy.cysjava.virtualwallet.service;

import com.alkemy.cysjava.virtualwallet.DTOs.CryptoCreationDTO;
import com.alkemy.cysjava.virtualwallet.mappers.CryptoMapper;
import com.alkemy.cysjava.virtualwallet.repositories.CryptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CryptoService {
    private final CryptoRepository cryptoRepository;
    private final CryptoMapper cryptoMapper;
    public CryptoService(CryptoRepository cryptoRepository, CryptoMapper cryptoMapper) {
        this.cryptoMapper = cryptoMapper;
        this.cryptoRepository = cryptoRepository;
    }

    public CryptoCreationDTO addCrypto(CryptoCreationDTO cryptoCreationDTO) {
        // Aquí puedes agregar lógica para validar o procesar más datos si es necesario
        
        return cryptoCreationDTO;
    }
}
