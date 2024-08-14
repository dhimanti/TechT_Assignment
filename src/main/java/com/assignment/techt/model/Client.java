package com.assignment.techt.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String username;
    private String password;
    private LocalDate dateOfBirth;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> phones;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> emails;

    @OneToOne(cascade = CascadeType.ALL)
    private BankAccount bankAccount;

    // Getters and setters...
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<String> getPhones() {
        return phones;
    }

    public void setPhones(Set<String> phones) {
        this.phones = phones;
    }

    public Set<String> getEmails() {
        return emails;
    }

    public void setEmails(Set<String> emails) {
        this.emails = emails;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }



    public void addPhone(String phone) {
        phones.add(phone);
    }

    public void removePhone(String phone) {
        if (phones.size() <= 1) {
            throw new IllegalArgumentException("Client must have at least one phone number.");
        }
        phones.remove(phone);
    }

    public void addEmail(String email) {
        emails.add(email);
    }

    public void removeEmail(String email) {
        if (emails.size() <= 1) {
            throw new IllegalArgumentException("Client must have at least one email.");
        }
        emails.remove(email);
    }
}
