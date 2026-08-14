# 💳 PaySplit – Salary & Expense Tracker

> A modern, full-stack salary and expense tracking application built to help users log monthly income, track categorized expenses, analyze budget utilization, and visualize spending breakdown with precision.

---

## 🛠️ Tech Stack

### **Frontend**
![React](https://img.shields.io/badge/React-19.x-61DAFB?style=for-the-badge&logo=react&logoColor=black)
![Vite](https://img.shields.io/badge/Vite-8.x-646CFF?style=for-the-badge&logo=vite&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-ES6+-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)
![CSS3](https://img.shields.io/badge/CSS3-Modern_Dark_Theme-1572B6?style=for-the-badge&logo=css3&logoColor=white)
![Recharts](https://img.shields.io/badge/Recharts-Data_Visualization-22B5BF?style=for-the-badge)
![Axios](https://img.shields.io/badge/Axios-HTTP_Client-5A29E4?style=for-the-badge&logo=axios&logoColor=white)

### **Backend**
![Java](https://img.shields.io/badge/Java-21-007396?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.3-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-JWT-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)
![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-Hibernate-6DB33F?style=for-the-badge)

### **Database & Containerization**
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-Containerized-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Docker Compose](https://img.shields.io/badge/Docker_Compose-Orchestration-2496ED?style=for-the-badge&logo=docker&logoColor=white)

---

## ✨ Features

- 🔐 **Secure Authentication & Verification**: Complete user registration with email OTP verification and JWT-based session security.
- 💵 **Monthly Budget Planning**: Set custom monthly income/salary targets with instant quick-preset values.
- 📝 **Categorized Expense Ledger**: Log individual spending entries with custom notes and one-click presets (*Rent, Groceries, Utilities, Dining, Internet*).
- 📊 **Real-Time Financial Dashboard**: Track Total Salary, Total Spent, Remaining Balance, and budget usage progress bar in real time.
- 📈 **Interactive Spending Visualizations**: Toggle between dynamic **Donut Charts** (powered by Recharts) and detailed **Tabular Summaries** with percentage breakdowns.
- 🐳 **Docker Orchestrated Backend**: Fully containerized Spring Boot API and MySQL database for zero-friction backend startup.

---

## 📸 Application Walkthrough

### **Step 1: Account Creation**
Register a new account with Full Name, Email, and Password.
![Account Creation](<Screenshot 2026-08-14 224052.png>)

---

### **Step 2: Email Verification (OTP)**
Enter the 6-digit verification code sent to your registered email address.
![Email Verification](<Screenshot 2026-08-14 224201.png>)

---

### **Step 3: Docker Container Logs (OTP Output)**
In local development, OTP codes are logged directly inside the Docker backend container stdout logs.
![Docker Logs](<Screenshot 2026-08-14 224241.png>)

---

### **Step 4: User Authentication (Login)**
Log in using your verified email address and password to obtain a secure JWT session token.
![Login Page](<Screenshot 2026-08-14 224309.png>)

---

### **Step 5: Budget & Expense Input Workspace**
Define your monthly salary budget and easily log expense entries using category preset quick-buttons.
![Financial Overview Setup](<Screenshot 2026-08-14 224341.png>)

---

### **Step 6: Real-Time Financial Dashboard**
Monitor Monthly Salary, Total Spent, Remaining Balance, and budget utilization status bar in real time.
![Dashboard Overview](<Screenshot 2026-08-14 224525.png>)

---

### **Step 7: Transaction Ledger Table**
Inspect detailed record of all monthly entries with category notes, creation timestamps, and single-click deletion.
![Transaction Ledger](<Screenshot 2026-08-14 224543.png>)

---

### **Step 8: Interactive Spending Breakdown (Donut Chart)**
Visualize expenditure proportions by category with dynamic charts rendered via Recharts.
![Donut Chart View](<Screenshot 2026-08-14 224557.png>)

---

### **Step 9: Interactive Spending Breakdown (Table View)**
Toggle to tabular view for clear numeric analysis showing exact amounts and total percentage contributions per category.
![Tabular Category View](<Screenshot 2026-08-14 224609.png>)

---

## 📁 Repository Structure

```text
PaySplit/
├── backend/                  # Java 21 + Spring Boot REST API
│   ├── src/                  # Controllers, Services, Models, Security, Repositories
│   ├── Dockerfile            # Multi-stage Docker build for Spring Boot application
│   ├── pom.xml               # Maven dependencies (Spring Boot, Security, JPA, JWT, MySQL)
│   └── mvnw.cmd / mvnw       # Maven wrapper scripts
├── frontend/                 # React 19 + Vite Frontend Application
│   ├── src/                  # React components, pages, services, styling
│   ├── package.json          # Node dependencies (React, Vite, Recharts, Axios, Router)
│   └── vite.config.js        # Vite configuration
├── docker-compose.yml        # Docker service configuration for MySQL & Backend
├── docker-compose.yml.example# Example environment configuration for Docker Compose
└── README.md                 # Project documentation
```

---

## 🔑 Environment Variables & Security

| Container / Module | Property | Default Value | Notes |
| :--- | :--- | :--- | :--- |
| **MySQL Container** | `MYSQL_ROOT_PASSWORD` | `rootpassword` | Database root access |
| **MySQL Container** | `MYSQL_DATABASE` | `paysplit_db` | Main application DB |
| **Backend API** | `SPRING_DATASOURCE_URL` | `jdbc:mysql://mysql:3306/paysplit_db` | Docker internal network DB link |
| **Backend API** | `jwt.secret` | `8f3d9a2b...` | HMAC-SHA secret key for JWT signing |

---

## 📡 Key REST API Endpoints

| Method | Endpoint | Description | Auth Required |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/auth/register` | Register a new user account | ❌ No |
| `POST` | `/api/auth/verify-otp` | Verify email with 6-digit OTP | ❌ No |
| `POST` | `/api/auth/login` | Authenticate user & receive JWT token | ❌ No |
| `GET/POST` | `/api/budget` | Fetch or update monthly salary setting | 🔒 Yes (Bearer JWT) |
| `GET/POST/DELETE` | `/api/transactions` | Manage monthly expense items | 🔒 Yes (Bearer JWT) |

---

## ⚡ Prerequisites

Make sure you have the following installed on your machine before getting started:

1. [Docker Desktop](https://www.docker.com/products/docker-desktop/) (Required for Backend & MySQL)
2. [Node.js (v18 or higher)](https://nodejs.org/) & `npm` (Required for Frontend)
3. [Git](https://git-scm.com/)
4. *(Optional)* [JDK 21](https://adoptium.net/) (Only required if running backend manually without Docker)

---

## 🚀 Step-by-Step Local Setup & Execution

### **Step 1: Clone the Repository**

```bash
git clone https://github.com/YOUR_USERNAME/PaySplit.git
cd PaySplit
```

---

### **Step 2: Start Backend & Database (Using Docker)**

The backend API and MySQL database run together inside Docker containers using `docker-compose`.

1. Open Docker Desktop on your machine.
2. From the root `PaySplit` directory, run:

```bash
docker compose up --build -d
```

> 💡 **Note**: Docker will spin up:
> - **MySQL Container**: Available on port `3307` (database: `paysplit_db`).
> - **Spring Boot Backend Container**: Available on port `8080`.

To view running containers and live backend logs:
```bash
docker compose logs -f backend
```

---

### **Step 3: Start Frontend (Using VS Code / Terminal)**

The frontend is executed locally via Node.js / Vite.

1. Open a new terminal window (or VS Code integrated terminal).
2. Navigate to the `frontend` folder:

```bash
cd frontend
```

3. Install dependencies:

```bash
npm install
```

4. Launch the Vite development server:

```bash
npm run dev
```

5. Open your browser and navigate to:
```text
http://localhost:5173
```

---

### **Alternative: Running Backend Manually (Without Docker)**

If you prefer to run the Spring Boot backend without Docker:

1. Ensure MySQL server is running locally on port `3306` with database `paysplit_db`.
2. Update `backend/src/main/resources/application.yml` with your local MySQL credentials.
3. Open a terminal in `backend/` and run:

**Windows (PowerShell/CMD):**
```powershell
cd backend
.\mvnw.cmd spring-boot:run
```

**Linux / macOS:**
```bash
cd backend
./mvnw spring-boot:run
```

---

## 🤝 Contributing

Contributions are welcome! Feel free to open an **Issue** or submit a **Pull Request** if you have suggestions, bug fixes, or new feature enhancements.

---

## 📜 License

This project is open-source and available under the [MIT License](LICENSE).