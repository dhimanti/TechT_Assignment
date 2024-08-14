package com.assignment.techt.service;

import com.assignment.techt.dto.ClientDto;
import com.assignment.techt.dto.ClientSearchCriteria;
import com.assignment.techt.exception.ClientAlreadyExistsException;
import com.assignment.techt.exception.ClientNotFoundException;
import com.assignment.techt.model.BankAccount;
import com.assignment.techt.model.Client;
import com.assignment.techt.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    public boolean isEmailAlreadyExists(String email) {
        return clientRepository.existsByEmailsContaining(email);
    }

    public Client createClient(ClientDto clientDto) {
        if (clientDto.getEmails() == null || clientDto.getEmails().isEmpty()) {
            throw new IllegalArgumentException("Client must have at least one email.");
        }
        String primaryEmail = clientDto.getEmails().iterator().next();
        if (clientRepository.existsByEmailsContaining(primaryEmail)) {
            throw new ClientAlreadyExistsException("Email already exists: " + primaryEmail);
        }
        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(clientDto.getBalance());
        bankAccount.setInitialBalance(clientDto.getBalance());

        Client client = new Client();
        client.setName(clientDto.getName());
        client.setUsername(clientDto.getUsername());
        client.setPassword(clientDto.getPassword());
        client.setDateOfBirth(clientDto.getDateOfBirth());
        client.setPhones(clientDto.getPhones());
        client.setEmails(clientDto.getEmails());
        client.setBankAccount(bankAccount);

        return clientRepository.save(client);
    }

    public Client updateClientPhones(Long clientId, Set<String> phones) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new ClientNotFoundException(clientId));
        if (phones.isEmpty()) {
            throw new IllegalArgumentException("Client must have at least one phone number.");
        }
        client.setPhones(phones);
        return clientRepository.save(client);
    }

    public Client updateClientEmails(Long clientId, Set<String> emails) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new ClientNotFoundException(clientId));
        if (emails.isEmpty()) {
            throw new IllegalArgumentException("Client must have at least one email.");
        }
        client.setEmails(emails);
        return clientRepository.save(client);
    }

    public Client addPhone(Long clientId, String phone) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new ClientNotFoundException(clientId));
        if (client.getPhones().isEmpty()) {
            throw new IllegalArgumentException("Client must have at least one phone number.");
        }
        client.addPhone(phone);
        return clientRepository.save(client);
    }

    public Client removePhone(Long clientId, String phone) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new ClientNotFoundException(clientId));
        client.removePhone(phone);
        if (client.getPhones().isEmpty()) {
            throw new IllegalArgumentException("Client must have at least one phone number.");
        }
        return clientRepository.save(client);
    }

    public Client addEmail(Long clientId, String email) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new ClientNotFoundException(clientId));
        if (client.getEmails().isEmpty()) {
            throw new IllegalArgumentException("Client must have at least one email.");
        }
        client.addEmail(email);
        return clientRepository.save(client);
    }

    public Client removeEmail(Long clientId, String email) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new ClientNotFoundException(clientId));
        client.removeEmail(email);
        if (client.getEmails().isEmpty()) {
            throw new IllegalArgumentException("Client must have at least one email.");
        }
        return clientRepository.save(client);
    }

    public List<Client> searchClients(ClientSearchCriteria searchCriteria, Pageable pageable) {
        if (searchCriteria.getName() != null) {
            return clientRepository.findByNameStartingWith(searchCriteria.getName(), pageable);
        } else if (searchCriteria.getPhone() != null) {
            return clientRepository.findByPhonesContains(searchCriteria.getPhone(), pageable);
        } else if (searchCriteria.getEmail() != null) {
            return clientRepository.findByEmailsContains(searchCriteria.getEmail(), pageable);
        } else if (searchCriteria.getDateOfBirth() != null) {
            return clientRepository.findByDateOfBirthGreaterThanEqual(searchCriteria.getDateOfBirth(), pageable);
        }
        throw new IllegalArgumentException("Invalid search criteria");
    }
}
