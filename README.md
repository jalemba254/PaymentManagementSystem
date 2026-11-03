\# Payment Management System



A Java-based console application for managing payment transactions. The project demonstrates object-oriented programming, input validation, file-based persistence, Maven project management, and automated testing with JUnit 5.



\## Features



\- Register customer payments.

\- Generate unique transaction IDs.

\- Support M-Pesa, Bank, and Cash payment methods.

\- Validate customer names, payment amounts, and payment methods.

\- View all recorded payments.

\- Search transactions by payment ID.

\- Update payment statuses: PAID, FAILED, and PENDING.

\- Generate payment summary reports.

\- Save and load payment records locally.



\## Technologies



\- Java 17 compatibility

\- Apache Maven

\- JUnit 5

\- Java Object Serialization



\## Project Structure



```text

PaymentManagementSystem/

├── pom.xml

├── README.md

├── .gitignore

└── src/

&#x20;   ├── main/

&#x20;   │   └── java/

&#x20;   │       ├── Payment.java

&#x20;   │       ├── PaymentStorage.java

&#x20;   │       ├── PaymentService.java

&#x20;   │       └── PaymentManagementSystem.java

&#x20;   └── test/

&#x20;       └── java/

&#x20;           └── PaymentServiceTest.java

```



\## Requirements



\- JDK 17 or later

\- Apache Maven 3.8 or later



\## Build the Project



Run:



```bash

mvn clean package

```



\## Run Automated Tests



Run:



```bash

mvn clean test

```



The project currently includes eight automated JUnit tests covering payment registration, validation, transaction ID generation, and payment status updates.



\## Run the Application



After building, run:



```bash

java -cp target/classes PaymentManagementSystem

```



\## Learning Objectives



This project demonstrates practical experience with:



\- Java classes and objects

\- Encapsulation

\- ArrayList collections

\- Input validation

\- Exception handling

\- File handling and serialization

\- Maven dependency management and builds

\- JUnit 5 automated testing



\## Project Limitations



This is an educational portfolio project. It does not connect to live M-Pesa, bank APIs, payment gateways, or production databases. Payment records are stored locally using Java serialization.



\## Author



\*\*Collins Jalemba\*\*



GitHub: https://github.com/jalemba254



