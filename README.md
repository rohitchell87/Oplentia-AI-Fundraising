# Oplentia

Oplentia is a platform that connects **entrepreneurs with stakeholders and potential funders** based on the reach, quality, and impact of their ideas.
The goal of the platform is to create a **startup discovery ecosystem** where innovative ideas can gain visibility and attract support from investors and collaborators.

This project is built using **Spring Boot, Java, and Thymeleaf**, providing a full-stack web application with server-side rendering and responsive UI.

---

# Features

* Startup idea submission and discovery
* Interactive startup exploration
* Entrepreneur–investor connection platform
* Leaderboard for trending startups
* User authentication (login/signup)
* Personal startup dashboard
* Profile management
* Responsive UI using Bootstrap
* Real-time leaderboard updates using WebSockets

---

# Tech Stack

## Backend

* Java 17
* Spring Boot
* Spring MVC
* WebSockets

## Frontend

* Thymeleaf
* HTML5
* CSS3
* Bootstrap 5

## Build Tool

* Maven

## Other Tools

* Git
* GitHub

---

# Project Structure

```text
Oplentia
│
├── src
│   └── main
│       ├── java
│       │   └── com
│       │       └── oplentia
│       │           ├── HomeController.java
│       │           ├── Idea.java
│       │           ├── IdeaRepository.java
│       │           ├── LeaderboardWebSocketHandler.java
│       │           ├── User.java
│       │           ├── UserRepository.java
│       │           ├── WebSocketConfig.java
│       │           └── OplentiaApplication.java
│       │
│       └── resources
│           ├── templates
│           │   ├── login.html
│           │   ├── signup.html
│           │   ├── submit.html
│           │   ├── leaderboard.html
│           │   ├── profile.html
│           │   └── my-startup.html
│           │
│           ├── static
│           │   └── css
│           │       └── style.css
│           │
│           └── application.properties
│
├── pom.xml
└── README.md
```

---

# Prerequisites

Before running the project, ensure you have the following installed:

* **Java JDK 17**
* **Maven**
* **Git**
* A modern web browser

You can check installations using:

```bash
java -version
mvn -version
git --version
```

---

# Installation

Clone the repository:

```bash
git clone https://github.com/rohitchell87/Oplentia-AI-Fundraising.git
```

Navigate to the project folder:

```bash
cd Oplentia-AI-Fundraising
```

---

# Running the Application

Run the Spring Boot application using Maven:

```bash
mvn spring-boot:run
```

Once the server starts, open your browser and go to:

```
http://localhost:8080
```

---

# Application Pages

### Home

Overview of the platform and introduction to Oplentia.

### Login / Signup

User authentication system for entrepreneurs and platform participants.

### Submit Startup

Entrepreneurs can submit their startup ideas.

### Leaderboard

Displays trending and top-performing startup ideas.

### My Startup

Personal dashboard for entrepreneurs to manage their startup submissions.

### Profile

User profile management page.

---

# Future Improvements

* AI-powered startup evaluation system
* Investor matchmaking algorithm
* Startup funding simulation
* Real-time analytics dashboard
* Cloud deployment
* REST API for mobile integration

---

# Author

Rohit Sharma
B.Tech CSE
IIIT Delhi

---

# License

This project is licensed under the **MIT License**.
