# AASTU ID Card Replacement (Appointment) System

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
# ![icon](https://github.com/DeepBlue-dot/ID-card-Replacement-system/blob/main/Client/src/main/resources/org/IDentifyMe/icons/iconSmall.png?raw=true)

### Table of Contents

1. [Introduction](#introduction)
2. [Objective](#objective)
3. [Technology Stack](#technology-stack)
4. [Repository Structure](#repository-structure)
5. [Installation](#installation)
6. [Usage](#usage)
7. [Directory Structure](#directory-structure)
8. [Database Dump](#database-dump)

---

### Introduction

The ID Card Replacement (Appointment) System for AASTU Students aims to streamline the process of replacing lost or damaged ID cards at AASTU. The current process is often cumbersome and time-consuming for both students and administrative staff. This system aims to modernize and simplify the process, improving the overall student experience and administrative efficiency.

### Objective

The goal of this initiative is to make it easier for AASTU students to replace ID cards that have been misplaced or destroyed. By implementing this system, we aim to improve the overall student experience, increase transparency, and expedite administrative operations.

### Technology Stack

- **Client-side**: JavaFX for the user interface.
- **Server-side**: Undertow for the server and MySQL database for data storage.
- **Other Technologies**: JSON for data interchange.

### Repository Structure

- **Client**: Contains the client-side code, including JavaFX controllers and views.
- **Server**: Contains the server-side code, including database management and API endpoints.

---

### Installation

To install and run this project, follow these steps:

1. **Clone the Repository**: 
   ```
   git clone <repository-url>
   ```
2. **Setup Database**:
   - Install MySQL and create a new database.
   - Import the database dump file located in `Server/src/main/java/com/IDentifyMe/database/dump` to populate the database schema and initial data.
     ```
     mysql -u username -p database_name < dump_file.sql
     ```
3. **Configure Server**:
   - Open the server project in your IDE.
   - Update the database connection settings in `DatabaseManager.java` to match your MySQL database configuration.
4. **Build Server**:
   - Navigate to the server directory.
     ```
     cd Server
     ```
   - Build the server application using Maven.
     ```
     mvn clean install
     ```
5. **Run Server**:
   - Execute the server JAR file.
     ```
     java -jar target/IDentifyMe-1.0-SNAPSHOT.jar
     ```
6. **Configure Client**:
   - Open the client project in your IDE.
   - Update the server URL in `HttpClientHandler.java` to match the server's endpoint.
7. **Build Client**:
   - Navigate to the client directory.
     ```
     cd Client
     ```
   - Build the client application using Maven.
     ```
     mvn clean install
     ```
8. **Run Client**:
   - Execute the client JAR file.
     ```
     java -jar target/IDentifyMe-1.0-SNAPSHOT.jar
     ```

### Troubleshooting

If you encounter any issues during installation or setup, try the following troubleshooting steps:

- **Check Dependencies**: Ensure that all required dependencies are installed and configured correctly.
- **Verify Database Connection**: Double-check the database connection settings in `DatabaseManager.java` and ensure they match your MySQL database configuration.
- **Build Errors**: If you encounter build errors, make sure that Maven is installed and configured properly. Check for any missing dependencies or syntax errors in the code.
- **Server Not Starting**: If the server fails to start, check the console output for any error messages. Verify that the database connection is established and that the server is listening on the correct port.

If you're unable to resolve the issue, feel free to reach out for assistance.



### Usage

To use the ID Card Replacement (Appointment) System, follow these steps:

1. **Login**: Enter your credentials to access the system.
2. **Request Replacement**: Submit a request for a replacement ID card.
3. **View Status**: Check the status of your replacement request.
4. **Admin Dashboard**: Administrators can manage requests and view student information.

### Directory Structure

- **Client**: Contains the client-side code.
  - `src/main/java/org/IDentifyMe`: JavaFX controllers and classes.
  - `src/main/resources/org/IDentifyMe`: FXML files for UI layout and CSS files for styling.
- **Server**: Contains the server-side code.
  - `src/main/java/com/IDentifyMe`: Server-side Java classes.
  - `src/main/resources`: Server configuration files and resources.

### Database Dump

- **Database Dump**: Contains SQL dump files for the database schema and initial data.
   - `database/dump`: SQL dump files for database setup.

