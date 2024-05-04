# AASTU ID Card Replacement (Appointment) System

## Description
The ID Card Replacement System aims to simplify the process of replacing lost or damaged ID cards for students at the Addis Ababa Science and Technology University (AASTU). This system streamlines administrative procedures, enhances efficiency, and improves the overall student experience.

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
## ![alt text](https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.pngwing.com%2Fen%2Ffree-png-ntwfc&psig=AOvVaw3VlVREPZcuc_FgRmcHBHYx&ust=1714875908913000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCJDP5JP58oUDFQAAAAAdAAAAABAE)

## Table of Contents
- [Executive Summary](#executive-summary)
- [Introduction](#introduction)
- [Objectives](#objectives)
- [Scope](#scope)
- [Features](#features)
- [Methodology/Approach](#methodologyapproach)
- [User Interfaces](#user-interfaces)
- [Database Structure](#database-structure)
- [Project Structure](#project-structure)
- [Resources Required](#resources-required)
- [Timeline](#timeline)
- [Conclusion](#conclusion)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [Credits](#credits)
- [License](#license)
- [Contact](#contact)

## Executive Summary
The proposed project aims to simplify the process of replacing lost or damaged ID cards for students at the Addis Ababa Science and Technology University (AASTU). By implementing an ID Card Replacement (Appointment) System, AASTU can streamline administrative processes, enhance efficiency, and improve the overall student experience. This system will provide a user-friendly interface for students to request ID card replacements, facilitate document submission, and schedule appointments for collection. Additionally, it will offer interfaces for the finance department and the ID replacement department to manage requests efficiently. The project will utilize JavaFX for the user interface, Undertow for the server, and MySQL for the database, ensuring reliability, performance, and scalability.

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

## Methodology/Approach
The project will adopt an agile development methodology to iteratively design, develop, test, and deploy the ID Card Replacement (Appointment) System. Key activities will include requirements gathering, system design, implementation, testing, and deployment. The system will be developed using JavaFX for the user interface, Undertow for the server, and MySQL for the database. Regular feedback sessions with stakeholders will ensure alignment with user needs and requirements.

## User Interfaces
### Student Interface
- Students can log in using their credentials.
- Select request type: ID card renewal or replacement.
- Upload required documents.
- Make payment of 250 ETB.

### Finance Department Interface
- Log in with department credentials.
- View pending requests.
- Verify banking information.
- Approve or deny requests.

### ID Replacement Department Interface
- Log in with department credentials.
- Review approved requests.
- Verify submitted documents.
- Approve requests and set appointment dates.

## Database Structure
### Students Table
- StudentID (Primary Key)
- Name
- Email
- Password (hashed and salted)

### Requests Table
- RequestID (Primary Key)
- StudentID (Foreign Key referencing Students Table)
- RequestType (Renewal or Replacement)
- RequestDate
- Status (Pending, Approved by Finance, Approved by ID Replacement, Denied)
- AppointmentDate (nullable)

### Documents Table
- DocumentID (Primary Key)
- RequestID (Foreign Key referencing Requests Table)
- DocumentType (Picture, Department Document, Police Document)
- DocumentPath (Path to the document stored securely)

### Payment Details Table
- PaymentID (Primary Key)
- RequestID (Foreign Key referencing Requests Table)
- Amount
- ReceiptNumber
- PaymentDate
- PaymentVerificationDate
- BankName

### Department Tables
- Finance Department Table:
  - EmployeeID (Primary Key)
  - Name
  - Email
  - Password (hashed and salted)

- ID Replacement Department Table:
  - EmployeeID (Primary Key)
  - Name
  - Email
  - Password (hashed and salted)

## Project Structure
- JavaFX for User Interface: Building rich client applications.
- Undertow for Server: High-performance web server with support for both blocking and non-blocking I/O.
- MySQL for Database: Popular open-source RDBMS known for reliability and scalability.
- Additional Libraries: Meavn, org.JSON, HTTPCLIENT

## Resources Required
- Development team: Java developers, UI/UX designers, database administrators
- Hardware: Servers for hosting the application
- Software: JavaFX, Undertow, MySQL
- Budget for development, testing, and deployment
- Stakeholder engagement and training resources

## Timeline
1. Requirements gathering and analysis
2. System design and architecture
3. Development and testing
4. Deployment and user training

## Conclusion
The proposed ID Card Replacement (Appointment) System presents a strategic opportunity for AASTU to enhance administrative efficiency, improve student services, and modernize campus processes. By implementing this system, AASTU can streamline the ID card replacement process, reduce administrative burden, and provide a better experience for students. The project aligns with the university's goals of innovation, excellence, and student-centricity.

## Installation
Provide step-by-step installation instructions.

## Usage
Explain how to use your project, including interfaces for students, the finance department, and the ID replacement department.

## Contributing
Guidelines for contributing to the project.

## Credits
Acknowledgements for individuals or projects that contributed to the project.

## License
This project is licensed under the [MIT License](LICENSE).

## Contact
For inquiries, please contact:

- Yassub Demisse (yassub@example.com)
- Yeabsira Fikadu (yeabsira@example.com)
- Yeabsira Mekonnen (yeabsira@example.com)
- Yeabsira G/Michael (yeabsira
