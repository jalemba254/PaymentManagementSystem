# Payment Management System

A Java console-based payment management application demonstrating object-oriented programming, payment validation, transaction management, and persistent file storage.

## Developer
Collins Jalemba

## Features
- Register customer payments.
- Generate unique transaction IDs.
- Validate customer details and payment amounts.
- Accept M-Pesa, Bank, and Cash payment methods.
- View all registered payments.
- Search payments by transaction ID.
- Update payment status.
- Generate payment reports.
- Automatically save and load payment records.

## Project Architecture

| File | Responsibility |
|---|---|
| Payment.java | Payment data model |
| PaymentService.java | Payment operations and business logic |
| PaymentStorage.java | File storage and retrieval |
| PaymentManagementSystem.java | Main application and user interface |

## Technologies
- Java
- Object-Oriented Programming
- ArrayList
- Encapsulation
- Java Serialization
- File Handling

## Requirements
Java Development Kit (JDK).

## How to Run

Compile all Java files:

    javac Payment.java PaymentStorage.java PaymentService.java PaymentManagementSystem.java

Run the application:

    java PaymentManagementSystem

## Data Storage

Payment records are stored locally in `payments.dat` using Java object serialization.

## Project Status
Functional educational portfolio project.

## Disclaimer
This is a learning and demonstration application. It does not process real financial transactions or connect to payment providers.
