# Customer Feedback Application

A simple yet powerful **Spring Boot** web application that allows users to submit, view, edit, and delete their feedback.  
Admins get a full dashboard to manage (CRUD) all user feedback.

Built with love using modern Spring technologies + Oracle Database + Thymeleaf.

## ✨ Features

- **User Registration & Login** (with Spring Security)
- **Submit new feedback**
- **View, Edit, and Delete** your own previous feedback
- **Admin Dashboard** – full CRUD operations on all feedback
- Secure authentication & role-based access (USER / ADMIN)
- Responsive & clean UI using Thymeleaf + basic CSS
- Oracle Database integration with Hibernate/JPA

## 🛠️ Tech Stack

- **Backend** — Spring Boot 3.2.x
- **Database** — Oracle Database (XE 21c recommended)
- **Frontend** — Thymeleaf + HTML5 + CSS
- **Security** — Spring Security (BCrypt password encoding)
- **ORM** — Spring Data JPA + Hibernate
- **Build Tool** — Maven
- **Java** — 25

## 🚀 Quick Start

### Prerequisites

- Java 25
- Maven
- Oracle Database (XE 19c or higher) running locally
  - SID/Service: `XE`
  - Create user: `feedback_user` / password: `feedback_pass`
  - Grant necessary privileges (`CREATE SESSION`, `CREATE TABLE`, etc.)

### Steps to Run

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/Feedback-App.git
   cd Feedback-App
