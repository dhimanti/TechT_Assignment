package com.assignment.techt;


import com.assignment.techt.model.BankAccount;
import com.assignment.techt.model.Client;
import com.assignment.techt.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void run(String... args) throws Exception {
        // Clear existing data
        clientRepository.deleteAll();

        // Add initial clients
        addClient("John Doe", "johndoe", "password123", LocalDate.of(1985, 6, 15),
                  Set.of("1234567890"), Set.of("john.doe@example.com"), BigDecimal.valueOf(1000));
        addClient("Jane Smith", "janesmith", "password123", LocalDate.of(1990, 12, 25),
                  Set.of("0987654321"), Set.of("jane.smith@example.com"), BigDecimal.valueOf(1500));
        addClient("Alice Johnson", "alicejohnson", "password123", LocalDate.of(1978, 3, 5),
                  Set.of("1112233445"), Set.of("alice.johnson@example.com"), BigDecimal.valueOf(2000));
    }

    private void addClient(String name, String username, String password, LocalDate dateOfBirth,
                           Set<String> phones, Set<String> emails, BigDecimal balance) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(balance);
        bankAccount.setInitialBalance(balance);

        Client client = new Client();
        client.setName(name);
        client.setUsername(username);
        client.setPassword(password);
        client.setDateOfBirth(dateOfBirth);
        client.setPhones(phones);
        client.setEmails(emails);
        client.setBankAccount(bankAccount);

        clientRepository.save(client);
    }
}
