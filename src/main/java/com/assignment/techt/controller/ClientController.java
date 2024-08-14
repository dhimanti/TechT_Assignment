package com.assignment.techt.controller;

import com.assignment.techt.dto.ClientDto;
import com.assignment.techt.dto.ClientSearchCriteria;
import com.assignment.techt.exception.ClientNotFoundException;
import com.assignment.techt.model.Client;
import com.assignment.techt.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/create")
    public ResponseEntity<String> createClient(@RequestBody ClientDto clientDto) {
        if (clientDto.getEmails() == null || clientDto.getEmails().isEmpty()) {
            return new ResponseEntity<>("Client must have at least one email.", HttpStatus.BAD_REQUEST);
        }

        String primaryEmail = clientDto.getEmails().iterator().next();
        if (clientService.isEmailAlreadyExists(primaryEmail)) {
            return new ResponseEntity<>("Email already exists: " + primaryEmail, HttpStatus.CONFLICT);
        }

        clientService.createClient(clientDto);
        return new ResponseEntity<>("Client created successfully.", HttpStatus.CREATED);
    }

    @PutMapping("/{clientId}/phones/add")
    public ResponseEntity<?> addPhone(@PathVariable Long clientId, @RequestParam String phone) {
        try {
            Client updatedClient = clientService.addPhone(clientId, phone);
            return new ResponseEntity<>(updatedClient, HttpStatus.OK);
        } catch (ClientNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{clientId}/phones/remove")
    public ResponseEntity<?> removePhone(@PathVariable Long clientId, @RequestParam String phone) {
        try {
            Client updatedClient = clientService.removePhone(clientId, phone);
            return new ResponseEntity<>(updatedClient, HttpStatus.OK);
        } catch (ClientNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{clientId}/emails/add")
    public ResponseEntity<?> addEmail(@PathVariable Long clientId, @RequestParam String email) {
        try {
            Client updatedClient = clientService.addEmail(clientId, email);
            return new ResponseEntity<>(updatedClient, HttpStatus.OK);
        } catch (ClientNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{clientId}/emails/remove")
    public ResponseEntity<?> removeEmail(@PathVariable Long clientId, @RequestParam String email) {
        try {
            Client updatedClient = clientService.removeEmail(clientId, email);
            return new ResponseEntity<>(updatedClient, HttpStatus.OK);
        } catch (ClientNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchClients(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String dateOfBirth, // dateOfBirth as String
            Pageable pageable) {
        try {
            LocalDate parsedDateOfBirth = dateOfBirth != null ? LocalDate.parse(dateOfBirth) : null;
            ClientSearchCriteria searchCriteria = new ClientSearchCriteria(name, phone, email, parsedDateOfBirth);
            List<Client> clients = clientService.searchClients(searchCriteria, pageable);
            return new ResponseEntity<>(clients, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (DateTimeParseException e) {
            return new ResponseEntity<>("Invalid date format. Use 'yyyy-MM-dd'.", HttpStatus.BAD_REQUEST);
        }
    }
}