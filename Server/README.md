## ID Card Replacement (Appointment) System - Server

### Introduction:
The server-side component of the ID Card Replacement (Appointment) System handles database management, API endpoints, and server configuration. It plays a crucial role in storing and managing student information, requests, documents, payment details, and more.

### Technology Stack:
- **Server**: Undertow, a lightweight web server.
- **Database**: MySQL for relational database management.

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

### Database Dump:
- **database/dump**: Contains SQL dump files for the database schema and data.