package com.assignment.techt.task;

import com.assignment.techt.model.Client;
import com.assignment.techt.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class BalanceUpdateTask {

    @Autowired
    private ClientRepository clientRepository;

    @Scheduled(fixedRate = 60000) // Every minute
    public void updateBalances() {
        List<Client> clients = clientRepository.findAll();
        for (Client client : clients) {
            BigDecimal currentBalance = client.getBankAccount().getBalance();
            BigDecimal initialBalance = client.getBankAccount().getInitialBalance();
            BigDecimal maxAllowedBalance = initialBalance.multiply(BigDecimal.valueOf(2.07));
            BigDecimal newBalance = currentBalance.multiply(BigDecimal.valueOf(1.05));

            if (newBalance.compareTo(maxAllowedBalance) > 0) {
                newBalance = maxAllowedBalance;
            }

            client.getBankAccount().setBalance(newBalance);
            clientRepository.save(client);
        }
    }
}
