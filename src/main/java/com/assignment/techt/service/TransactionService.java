//package com.assignment.techt.service;
//
//import com.assignment.techt.exception.ClientNotFoundException;
//import com.assignment.techt.model.Client;
//import com.assignment.techt.model.Transaction;
//import com.assignment.techt.repository.ClientRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//
//@Service
//public class TransactionService {
//
//    @Autowired
//    private ClientRepository clientRepository;
//
//    @Transactional
//    public void transferMoney(Long fromClientId, Long toClientId, BigDecimal amount) {
//        Client fromClient = clientRepository.findById(fromClientId)
//                .orElseThrow(() -> new ClientNotFoundException(fromClientId));
//        Client toClient = clientRepository.findById(toClientId)
//                .orElseThrow(() -> new ClientNotFoundException(toClientId));
//
//        BigDecimal fromBalance = fromClient.getBankAccount().getBalance();
//        if (fromBalance.compareTo(amount) < 0) {
//            throw new IllegalArgumentException("Insufficient funds");
//        }
//
//        fromClient.getBankAccount().setBalance(fromBalance.subtract(amount));
//        toClient.getBankAccount().setBalance(toClient.getBankAccount().getBalance().add(amount));
//
//        clientRepository.save(fromClient);
//        clientRepository.save(toClient);
//    }
//}

package com.assignment.techt.service;

import com.assignment.techt.exception.ClientNotFoundException;
import com.assignment.techt.model.Client;
import com.assignment.techt.model.Transaction;
import com.assignment.techt.repository.ClientRepository;
import com.assignment.techt.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransactionService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public void transferMoney(Long fromClientId, Long toClientId, BigDecimal amount) {
        Client fromClient = clientRepository.findById(fromClientId)
                .orElseThrow(() -> new ClientNotFoundException(fromClientId));
        Client toClient = clientRepository.findById(toClientId)
                .orElseThrow(() -> new ClientNotFoundException(toClientId));

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }

        if (fromClient.getBankAccount().getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        fromClient.getBankAccount().setBalance(fromClient.getBankAccount().getBalance().subtract(amount));
        toClient.getBankAccount().setBalance(toClient.getBankAccount().getBalance().add(amount));

        Transaction transaction = new Transaction();
        transaction.setFromClient(fromClient);
        transaction.setToClient(toClient);
        transaction.setAmount(amount);
        transaction.setTimestamp(LocalDateTime.now());

        clientRepository.save(fromClient);
        clientRepository.save(toClient);
        transactionRepository.save(transaction);
    }
}

