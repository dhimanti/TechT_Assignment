--
---- Switch to the newly created database
--\c online_banking;
--
---- Create the bank_account table
--CREATE TABLE bank_account (
--    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
--    balance DECIMAL(19, 2) NOT NULL
--);
--
---- Create the client table
--CREATE TABLE client (
--    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
--    name VARCHAR(255),
--    username VARCHAR(255),
--    password VARCHAR(255),
--    date_of_birth DATE,
--    bank_account_id BIGINT,
--    FOREIGN KEY (bank_account_id) REFERENCES bank_account(id)
--);
--
---- Create the client_phones table to store phone numbers associated with clients
--CREATE TABLE client_phones (
--    client_id BIGINT,
--    phone_number VARCHAR(255),
--    FOREIGN KEY (client_id) REFERENCES client(id),
--    PRIMARY KEY (client_id, phone_number)
--);
--
---- Create the client_emails table to store email addresses associated with clients
--CREATE TABLE client_emails (
--    client_id BIGINT,
--    email VARCHAR(255),
--    FOREIGN KEY (client_id) REFERENCES client(id),
--    PRIMARY KEY (client_id, email)
--);
--
---- Indexes to improve search performance (optional)
--CREATE INDEX idx_client_name ON client(name);
--CREATE INDEX idx_client_date_of_birth ON client(date_of_birth);
--CREATE INDEX idx_client_phones_phone_number ON client_phones(phone_number);
--CREATE INDEX idx_client_emails_email ON client_emails(email);
