
# Client Management and Banking System

## Overview

This project is a Spring Boot application designed for managing client accounts and handling banking transactions. It supports client creation, updating contact information, searching clients, and transferring money between accounts. The system also features automatic balance updates and ensures transactions are processed with proper validation and locking.

## Features

- **Client Management**: Create, update, and delete clients.
- **Contact Information**: Add and remove phone numbers and emails.
- **Client Search**: Search for clients using various criteria.
- **Money Transfer**: Transfer money between accounts with validation and locking.
- **Balance Update**: Automatically increase client balances by 5% every minute, capped at 207% of the initial balance.

## Project Structure

- **`model`**: Contains entity classes (`Client`, `Transaction`).
- **`repository`**: Contains JPA repository interfaces (`ClientRepository`, `TransactionRepository`).
- **`service`**: Contains business logic (`ClientService`, `TransactionService`, `BalanceUpdateService`).
- **`controller`**: Contains REST controllers (`ClientController`).
- **`config`**: Contains configuration classes (`SchedulingConfig`).

## Setup

1. **Clone the Repository**

   ```bash
   git clone https://github.com/dhimanti/TechT_Assignment.git
   cd <directory>
   
2. **Database Configuration**

The application uses an postgresql database. So create a database name ****'online_banking_service'****.

3. **Build and Run**

Ensure you have Java 17 and Maven installed. Run the following commands:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

## API Endpoints

1. Create Client : Create a new client with an initial balance.

    ```bash
    curl -X POST "http://localhost:8080/api/clients/create" \
    -H "Content-Type: application/json" \
    -d '{
    "name": "John Doe",
    "username": "johndoe",
    "password": "password123",
    "dateOfBirth": "1985-06-15",
    "phones": ["123-456-7890"],
    "emails": ["john.doe@example.com"],
    "balance": 100.00
    }'
2. Add Phone Number : Add a phone number to an existing client.

    ```bash
    curl -X PUT "http://localhost:8080/api/clients/{clientId}/phones/add" \
    -H "Content-Type: application/x-www-form-urlencoded" \
    -d 'phone=123-456-7891'
3. Remove Phone Number : Remove a phone number from an existing client.

    ```bash
    curl -X PUT "http://localhost:8080/api/clients/{clientId}/phones/remove" \
    -H "Content-Type: application/x-www-form-urlencoded" \
    -d 'phone=123-456-7890'
4. Add Email : Add an email address to an existing client.

    ```bash
    curl -X PUT "http://localhost:8080/api/clients/{clientId}/emails/add" \
    -H "Content-Type: application/x-www-form-urlencoded" \
    -d 'email=new.email@example.com'
5. Remove Email : Remove an email address from an existing client.

    ```bash
    curl -X PUT "http://localhost:8080/api/clients/{clientId}/emails/remove" \
    -H "Content-Type: application/x-www-form-urlencoded" \
    -d 'email=old.email@example.com'
6. Search Clients
   Search for clients based on various criteria.

    ```bash
    curl -X GET "http://localhost:8080/api/clients/search?name=John&phone=123-456-7890&email=john.doe@example.com&dateOfBirth=1985-06-15"
7. Transfer Money : Transfer money from one client's account to another.

    ```bash
    curl -X POST "http://localhost:8080/api/clients/transfer" \
    -H "Content-Type: application/json" \
    -d '{
    "fromClientId": 1,
    "toClientId": 2,
    "amount": 50.00
    }'
