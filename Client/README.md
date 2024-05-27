## ID Card Replacement (Appointment) System - Client

### Introduction:
The client-side component of the ID Card Replacement (Appointment) System is responsible for the user interface and interaction. It utilizes JavaFX to create a user-friendly interface for students and administrative staff to interact with the system.

### Technology Stack:
- **JavaFX**: Used for building the graphical user interface.
- **CSS**: Styling for the user interface.

### Directory Structure

Ensure your project directory has the following structure:

```
Client/
├── .mvn/
├── .vscode/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/
│   │   │       └── IDentifyMe/
│   │   │           ├── Classes/
│   │   │           │   ├── HttpClientHandler.java
│   │   │           │   ├── Router.java
│   │   │           │   └── validatorFuctory.java
│   │   │           ├── Controller/
│   │   │           │   ├── AboutPageController.java
│   │   │           │   ├── CalendarController.java
│   │   │           │   ├── financeHomeController.java
│   │   │           │   ├── financeRequestsController.java
│   │   │           │   ├── ID_DepartmentHomeController.java
│   │   │           │   ├── ID_DepartmentRequestsController.java
│   │   │           │   ├── LoginController.java
│   │   │           │   ├── profileController.java
│   │   │           │   ├── renewalRequestController.java
│   │   │           │   ├── replacementRequestController.java
│   │   │           │   ├── requestFirstPage.java
│   │   │           │   ├── requestStatusController.java
│   │   │           │   ├── StudentDashboardController.java
│   │   │           │   └── StudentHomeController.java
│   │   │           ├── Models/
│   │   │           │   └── PaymentDetail.java
│   │   │           └── MainApp.java
│   │   └── resources/
│   │       └── org/
│   │           └── IDentifyMe/
│   │               ├── css/
│   │               │   ├── bootstrap.css
│   │               │   ├── fullpackstyling.css
│   │               │   ├── inputfiled.css
│   │               │   ├── loginPage.css
│   │               │   ├── styles.css
│   │               │   └── tabpane.css
│   │               ├── icons/
│   │               │   └── icon.png
│   │               ├── image/
│   │               │   ├── loginBg.png
│   │               │   └── no-image.png
│   │               └── view/
│   │                   ├── AboutPage.fxml
│   │                   ├── CalendarPage.fxml
│   │                   ├── defultPage.fxml
│   │                   ├── FinanceDashboard.fxml
│   │                   ├── FinanceHome.fxml
│   │                   ├── financeRequestspage.fxml
│   │                   ├── ID_DepartmentDashboard.fxml
│   │                   ├── ID_DepartmentHome.fxml
│   │                   ├── idRequestspage.fxml
│   │                   ├── login.fxml
│   │                   ├── ProfilePage.fxml
│   │                   ├── renewalRequestPage.fxml
│   │                   ├── replacementRequestPage.fxml
│   │                   ├── requestFirstPage.fxml
│   │                   ├── requestStatusPage.fxml
│   │                   ├── StudentDashboard.fxml
│   │                   ├── StudentHome.fxml
│   │                   └── Students.fxml
├── target/
├── mvnw
├── mvnw.cmd
└── pom.xml
```

This directory structure represents the layout of the client project. Adjustments may be needed based on your specific project requirements.

---

These README files provide an overview of the project structure, technology stack, and directory contents for the ID Card Replacement (Appointment) System. They aim to assist developers in understanding and contributing to the project.