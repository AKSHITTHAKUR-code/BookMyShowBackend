# 🎬 BookMyShow Backend API

A robust RESTful backend application inspired by **BookMyShow**, built using **Java**, **Spring Boot**, **Spring Data JPA**, and **MySQL**. This project manages movie catalogs, theater listings across cities, show schedules, and concurrent seat reservations.

---

## 🚀 Key Features

* **Movie & Theater Catalog:** Browse active movies and filter theaters by city.
* **Show Scheduling:** Manage show timings, ticket pricing, and seat availability per theater.
* **Seat Management & Concurrency Control:** Uses **Optimistic Locking (`@Version`)** in JPA to prevent double-booking when multiple users attempt to reserve the same seat simultaneously.
* **Clean Layered Architecture:** Strictly follows the `Controller` → `Service` → `Repository` → `Entity/DTO` design pattern.

---

## 🛠️ Tech Stack

* **Language:** Java 21
* **Framework:** Spring Boot 3.x (Spring Web, Spring Data JPA)
* **Database:** MySQL
* **Build Tool:** Maven

---

## 📂 Project Structure

```text
src/main/java/com/codeworld/BookMyShowBackend
├── controller      # REST Controllers handling HTTP requests
├── dto             # Data Transfer Objects for clean API responses
├── entity          # JPA Entities (Movie, Theater, Show, ShowSeat, etc.)
├── repository      # Spring Data JPA Repositories
└── service         # Business logic and transaction management
```

---

## ⚙️ Setup & Installation

### 1. Prerequisites
* Java 21 or higher
* Maven
* MySQL Server

### 2. Configure Database
Create a database named `bookmyshow` in MySQL and update `src/main/resources/application.properties` with your credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bookmyshow?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD
spring.jpa.hibernate.ddl-auto=update
```

### 3. Run the Application
```bash
mvn spring-boot:run
```
Once the application starts and Hibernate generates the schema, you can populate sample movies, theaters, shows, and seats using the SQL seed script.

---

## 📌 Sample API Endpoints

### Theaters
* **Get theaters by city:**
  ```http
  GET /api/v1/theaters?city=Bengaluru
  ```

### Movies & Shows *(Extendable Endpoints)*
* **Get all active movies:** `GET /api/v1/movies`
* **Get shows by movie/theater:** `GET /api/v1/shows`
* **Book seats:** `POST /api/v1/bookings`

---

## 👨‍💻 Author
Developed as a Java Backend portfolio project demonstrating REST API design, relational database modeling, and concurrency handling in Spring Boot.
