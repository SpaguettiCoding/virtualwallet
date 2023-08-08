package com.alkemy.cysjava.virtualwallet.controllers;

import com.alkemy.cysjava.virtualwallet.DTOs.CreditCardCreationDTO;
import com.alkemy.cysjava.virtualwallet.DTOs.CreditCardDTO;
import com.alkemy.cysjava.virtualwallet.exceptions.ResourceNotFoundException;
import com.alkemy.cysjava.virtualwallet.models.CreditCard;
import com.alkemy.cysjava.virtualwallet.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/credit-card")
public class CreditCardController {

    private final CreditCardService creditCardService;

    @Autowired
    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

  @PostMapping("/register")
    public ResponseEntity<CreditCard> createCreditCard(@RequestBody CreditCardCreationDTO creditCardDTO) {
        CreditCard createdCreditCard = creditCardService.createCreditCard(creditCardDTO);
        return new ResponseEntity<>(createdCreditCard, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CreditCardDTO>> getAllCreditCards() {
        try {
            List<CreditCardDTO> creditCardDTOs = creditCardService.getAllCreditCards();
            return new ResponseEntity<>(creditCardDTOs, HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception or handle it as needed
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCreditCardById(@PathVariable("id") Long id) {
        creditCardService.deleteCreditCardById(id);
        return new ResponseEntity<>("Deletion of CreditCard with ID number " + id + " was successful.", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditCardDTO> getCreditCardById(@PathVariable Long id){
        try {
            CreditCardDTO creditCardDTO = creditCardService.findCreditCardById(id);
            return new ResponseEntity<>(creditCardDTO, HttpStatus.OK);
        }
        catch(ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}