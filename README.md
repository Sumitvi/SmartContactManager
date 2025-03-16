# SmartContactManager

## Overview
Smart Contact Manager is a web-based application developed using Spring Boot that allows users to store and manage their contacts efficiently. The application provides authentication, authorization, and cloud-based image storage.

## Features
- **User Authentication & Authorization** (Spring Security)
- **Contact Management** (Add, Edit, Delete, View Contacts)
- **User Profile Management**
- **Cloudinary Integration** for image storage
- **Responsive UI** using Thymeleaf
- **Database Management** with Spring Data JPA & MySQL

## Tech Stack
- **Backend:** Spring Boot, Spring MVC, Spring Security, Spring Data JPA
- **Frontend:** Thymeleaf, HTML, CSS, JavaScript
- **Database:** MySQL
- **Cloud Storage:** Cloudinary
- **Dependencies:** Lombok, Maven

## Installation & Setup

### Prerequisites
- Java 17+
- MySQL Server
- Maven

### Steps
1. **Clone the Repository**
   ```bash
   git clone https://github.com/your-username/smart-contact-manager.git
   cd smart-contact-manager
   ```
2. **Configure Database**
   - Update `application.properties` with your MySQL credentials:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/smart_contact_manager
     spring.datasource.username=root
     spring.datasource.password=yourpassword
     spring.jpa.hibernate.ddl-auto=update
     ```
3. **Configure Cloudinary** (Optional for image uploads)
   - Add your Cloudinary API credentials in `application.properties`:
     ```properties
     cloudinary.cloud-name=your-cloud-name
     cloudinary.api-key=your-api-key
     cloudinary.api-secret=your-api-secret
     ```
4. **Build & Run the Application**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
5. **Access the Application**
   - Open your browser and go to `http://localhost:8080`

## Usage
- **Register/Login** to manage your contacts
- **Add/Edit/Delete** contacts from your dashboard
- **Upload Profile & Contact Images** (if Cloudinary is configured)
- 

## License
MIT License

---
Developed by Sumit Vishwakarma
