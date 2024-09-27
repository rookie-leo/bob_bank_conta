CREATE DATABASE IF NOT EXISTS contadb;

USE contadb;

CREATE TABLE conta(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id VARCHAR(50) NOT NULL,
    account_number VARCHAR(10) NOT NULL UNIQUE,
    account_balance DECIMAL(15, 2) NOT NULL
);