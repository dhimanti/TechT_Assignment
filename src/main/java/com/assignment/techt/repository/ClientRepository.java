package com.assignment.techt.repository;

import com.assignment.techt.model.Client;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByEmailsContaining(String email);
    List<Client> findByNameStartingWith(String name, Pageable pageable);

    List<Client> findByPhonesContains(String phone, Pageable pageable);

    List<Client> findByEmailsContains(String email, Pageable pageable);

    List<Client> findByDateOfBirthGreaterThanEqual(LocalDate dateOfBirth, Pageable pageable);
}

