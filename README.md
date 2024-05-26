# AASTU ID Card Replacement (Appointment) System

## Description
The ID Card Replacement System aims to simplify the process of replacing lost or damaged ID cards for students at the Addis Ababa Science and Technology University (AASTU). This system streamlines administrative procedures, enhances efficiency, and improves the overall student experience.

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
## ![alt text](https://github.com/DeepBlue-dot/ID-card-Replacement-system/blob/main/Client/src/main/resources/org/IDentifyMe/icons/icon.png?raw=true)

## Table of Contents
- [Introduction](#introduction)
- [Objectives](#objectives)
- [Scope](#scope)
- [Features](#features)
- [Project Structure](#project-structure)
- [Resources Required](#resources-required)
- [Installation](#installation)
- [Credits](#credits)

## Introduction
The current process of replacing lost or damaged ID cards at AASTU is often cumbersome and time-consuming for both students and administrative staff. Students face challenges such as long waiting times, complex documentation requirements, and lack of transparency in the process. As a result, there is a need to modernize and streamline the ID card replacement process to better serve the student community. The proposed ID Card Replacement (Appointment) System aims to address these challenges by providing a user-friendly, efficient, and transparent solution.

## Objectives
- Simplify the process of replacing lost or damaged ID cards for AASTU students.
- Streamline administrative procedures associated with issuing replacement ID cards.
- Enhance efficiency and transparency in the ID card replacement process.
- Improve the overall student experience by providing a user-friendly interface for requesting ID card replacements.

## Scope
The project will involve the design, development, and implementation of an ID Card Replacement (Appointment) System specifically tailored for AASTU students. The system will include user interfaces for students, the finance department, and the ID replacement department. It will facilitate document submission, request approval, appointment scheduling, and database management. The scope does not include physical production of ID cards or integration with external systems beyond the university's infrastructure.

## Features
- Simplified Request Process: Students can easily request ID card renewal or replacement through a user-friendly interface.
- Document Upload: Depending on the request type, students can upload necessary documents directly through the system.
- Financial Approval: The finance department can review and approve requests based on provided banking information.
- ID Replacement Management: The ID replacement department can manage approved requests and schedule appointments for students to collect their new ID cards.

## Project Structure
- JavaFX for User Interface: Building rich client applications.
- Undertow for Server: High-performance web server with support for both blocking and non-blocking I/O.
- MySQL for Database: Popular open-source RDBMS known for reliability and scalability.
- Additional Libraries: Meavn, org.JSON, HTTPCLIENT

## Resources Required
- Development team: Java developers, UI/UX designers, database administrators
- Hardware: Servers for hosting the application
- Software: JavaFX, Undertow, MySQL

## Installation

### Prerequisites

Before you begin, ensure you have the following installed on your machine:

- **Java Development Kit (JDK) 17**: [Download and install JDK 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html).
- **Apache Maven**: [Download and install Maven](https://maven.apache.org/install.html).
- **Docker**: [Download and install Docker](https://docs.docker.com/get-docker/) (if you plan to use Docker).

### Installation

Follow these steps to set up and run the IDentifyMe application.

#### Step 1: Clone the Repository

First, clone the repository to your local machine:

```sh
git clone <repository_url>
cd IDentifyMe
```

#### Step 2: Build the Project

Use Maven to build the project:

```sh
./mvnw clean install
```

This command will compile the code, run tests, and package the application.

#### Step 3: Run the Application

You can run the application using either Maven or Docker.

#### Using Maven

```sh
./mvnw exec:java -Dexec.mainClass="com.IDentifyMe.App"
```

#### Using Docker

1. **Build the Docker Image**:

    ```sh
    docker build -t identifyme .
    ```

2. **Run the Docker Container**:

    ```sh
    docker run -p 8080:8080 identifyme
    ```

#### Step 4: Open in Browser

Once the application is running, open your web browser and go to:

```
http://localhost:8080
```

You should see the application's web interface.


### Directory Structure

Ensure your project directory has the following structure:

```
IDentifyMe/
├── .mvn/
├── .vscode/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── IDentifyMe/
│   │   │           ├── classes/
│   │   │           │   ├── DatabaseManager.java
│   │   │           │   └── Router.java
│   │   │           ├── controller/
│   │   │           │   ├── DocumentController.java
│   │   │           │   ├── FinanceDepartmentController.java
│   │   │           │   ├── IDReplacementDepartmentController.java
│   │   │           │   ├── PaymentDetailController.java
│   │   │           │   ├── RequestController.java
│   │   │           │   └── StudentController.java
│   │   │           ├── database/
│   │   │           │   ├── DocumentsTable.java
│   │   │           │   ├── FinanceDepartmentTable.java
│   │   │           │   ├── IDReplacementDepartmentTable.java
│   │   │           │   ├── PaymentDetailsTable.java
│   │   │           │   ├── RequestsTable.java
│   │   │           │   └── StudentsTable.java
│   │   │           ├── models/
│   │   │           │   ├── Document.java
│   │   │           │   ├── FinanceDepartment.java
│   │   │           │   ├── IDReplacementDepartment.java
│   │   │           │   ├── PaymentDetail.java
│   │   │           │   ├── Request.java
│   │   │           │   └── Student.java
│   │   │           └── App.java
│   │   └── resources/
│   │       ├── images/
│   │       │   ├── gtav.png
│   │       │   ├── icon.png
│   │       ├── webroot/
│   │       │   └── index.html
│   │       └── icon.png
│   └── test/
├── target/
├── Dockerfile
├── mvnw
├── mvnw.cmd
└── pom.xml
```

## Troubleshooting

- **Java Version**: Ensure you're using JDK 17. Check your Java version with:

    ```sh
    java -version
    ```

- **Maven Issues**: If you encounter issues with Maven, ensure that your `mvnw` script is executable:

    ```sh
    chmod +x mvnw
    ```

- **Dependencies**: If you face dependency issues, try updating the local repository:

    ```sh
    ./mvnw dependency:resolve
    ```

## Credits
Acknowledgements for individuals or projects that contributed to the project.

