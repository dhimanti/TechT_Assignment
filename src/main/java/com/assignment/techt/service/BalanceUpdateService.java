//package com.assignment.techt.service;
//
//import com.assignment.techt.model.Client;
//import com.assignment.techt.repository.ClientRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.util.List;
//
//@Service
//public class BalanceUpdateService {
//
//    @Autowired
//    private ClientRepository clientRepository;
//
//    @Scheduled(fixedRate = 60000) // Runs every minute
//    public void updateClientBalances() {
//        List<Client> clients = clientRepository.findAll();
//
//        for (Client client : clients) {
//            BigDecimal currentBalance = client.getBankAccount().getBalance();
//            BigDecimal initialBalance = client.getBankAccount().getInitialBalance();
//            BigDecimal maxBalance = initialBalance.multiply(BigDecimal.valueOf(2.07));
//            BigDecimal newBalance = currentBalance.multiply(BigDecimal.valueOf(1.05));
//
//            if (newBalance.compareTo(maxBalance) > 0) {
//                newBalance = maxBalance;
//            }
//
//            client.getBankAccount().setBalance(newBalance);
//            clientRepository.save(client);
//        }
//    }
//}
