package com.assignment.techt.dto;

import java.time.LocalDate;

public class ClientSearchCriteria {
    private String name;
    private String phone;
    private String email;
    private LocalDate dateOfBirth;

    public ClientSearchCriteria(String name, String phone, String email, LocalDate dateOfBirth) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    // Getters and setters...

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
