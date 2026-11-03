
# Payment Management System

A Java-based console application for managing customer payments, tracking transactions, and generating payment reports.

## Features

- Register customer payments.
- Generate unique transaction IDs.
- Support M-Pesa, Bank, and Cash payment methods.
- Validate customer details and payment amounts.
- View all recorded payments.
- Search payments by transaction ID.
- Update payment statuses (PAID, FAILED, PENDING).
- Generate payment reports.
- Save payment records using Java serialization.
- Migrate legacy payment records while preserving transaction details.
- Automated unit testing using JUnit 5.
- Maven project build and dependency management.

## Project Structure




PaymentManagementSystem/
├── pom.xml
├── README.md
├── .gitignore
└── src/
    ├── main/
    │   └── java/
    │       └── com/
    │           └── jalemba/
    │               └── payments/
    │                   ├── Payment.java
    │                   ├── PaymentStorage.java
    │                   ├── PaymentService.java
    │                   └── PaymentManagementSystem.java
    └── test/
        └── java/
            └── com/
                └── jalemba/
                    └── payments/
                        └── PaymentServiceTest.java
## Requirements

- Java JDK 17 or later
- Apache Maven 3.9 or later

## Build the Project

Run:

```bash
mvn clean package
```

## Run the Application

Run:

```bash
java -cp target/classes com.jalemba.payments.PaymentManagementSystem
```

## Run Automated Tests

Run:

```bash
mvn clean test
```

The project includes JUnit 5 tests covering payment registration, input validation, transaction IDs, and payment status updates.

## Data Storage

Payment records are stored locally in `payments.dat` using Java serialization.

This is an educational demonstration project. It does not connect to live M-Pesa or banking payment gateways.


