# Database_Security_System

### Database Tamper Detection & Automatic Restoration System

## 📌 Overview

**Database_Security_System** is a lightweight, Java-based security application that utilizes a **Tiled Bitmap Algorithm** to detect unauthorized modifications in database records.

Designed as a secure desktop application, it continuously monitors database data in the background, pinpoints the exact tampered fields, automatically restores them to their original state, and sends immediate email notifications to administrators when a breach occurs.

---

## 🎯 Objectives

* **Real-Time Detection:** Identify unauthorized changes in database records instantly.
* **Precision:** Pinpoint the specific field and row affected by tampering.
* **Auto-Recovery:** Automatically restore tampered data using secure backup hashes.
* **Alert System:** Notify the admin via automated email when tampering is detected.
* **Access Control:** Provide isolated, secure operational panels for both Admins and Clients.

---

## ✨ Features

* 🔍 **Tiled Bitmap Algorithm** for high-speed anomaly detection
* 🔄 **Automatic Data Restoration** (`DB_Restorer.java`)
* 📧 **Automated Email Notifications** (`SendEmail.java`)
* 👥 **Role-Based Access** (Dedicated Admin & Client portals)
* 📊 **Excel Data Export** (`XL_Operation.java`)
* 🖥️ **User-Friendly Desktop Interface** (Java Swing)
---

## 🛠️ Technologies Used

* **Frontend:** Java Swing (Apache NetBeans `.form` GUI builder)
* **Backend:** Java
* **Database:** MySQL & JDBC (`DB_Driver.java`)
* **Build Tool:** Maven (`pom.xml`)
* **Version Control:** Git & GitHub
---

## 🏗️ Project Architecture

```text

User Interface (Admin/Client Frames)
      ↓
Application Logic (Java Backend)
      ↓
Tiled Bitmap Algorithm (Tamper Detection)
      ↓
Database Verification (Hash Comparison)
      ↓
Restoration System (Auto-Recovery)
      ↓
MySQL Database
      ↓
Email Notification System

```
---

## 📂 Project Structure

```Plaintext

Database_Security_System/
│
├── src/main/java/
│   ├── admin_operation/      # Admin dashboard, login, and tamper monitoring
│   ├── client_operation/     # Client registration, data storage, and views
│   ├── db_engine/            # Core algorithm: Tamper Thread, Restorer, Emailer
│   ├── db_ops/               # Database connection drivers
│   └── db_security_tbm/      # Main runner class
│
├── src/main/resources/
│   ├── icons/                # UI icons
│   └── images/               # Application backgrounds and assets
│
├── Database_Security_System.sql
├── pom.xml                   # Maven dependencies
└── README.md

```
---

## 🔄 How It Works

1. **Connect:** The application establishes a secure JDBC connection to the MySQL database.
2. **Monitor:** `Tamper_Detection_Thread.java` runs continuously, checking records against stored bitmap hashes.
3. **Detect:** `TamperFieldIdentifier.java` detects unexpected modifications.
4. **Identify:** `Culprit_Finder.java` isolates the exact tampered field.
5. **Restore:** `DB_Restorer.java` retrieves the original value and automatically fixes the database.
6. **Alert:** `SendEmail.java` dispatches a security notification to the administrator.
---

## 🗄️ Database Setup

1. **Install MySQL:** Ensure MySQL Server and Workbench are installed locally.
2. **Create the Database:** Create a blank database in your MySQL server.
3. **Import the Backup:** Locate the `Database_Security_System.sql` file in the repository and import it to set up the required schema and tables.
4. **Configure Connection:** Open `src/main/java/db_ops/DB_Driver.java` and update the database URL, username, and password to match your local setup.
---

## ▶️ How to Run

**Prerequisites:** Java JDK, Maven, MySQL Server, and an IDE (like Apache NetBeans).
1. **Clone the repository:**
  ```bash
    git clone https://github.com/tanushkarale/Database_Security_System.git
  ```
2. **Open the Project:** Load the folder in Apache NetBeans.
3. **Setup Database:** Import the SQL backup and configure the `DB_Driver.java` credentials.
4. **Build:** Run a Clean and Build via Maven to resolve `pom.xml` dependencies.
5. **Execute:** Run the `Db_security_tbm.java` main class.
---

## 🔐 Security Features

* **Cryptographic Tamper Detection:** Uses algorithmic hashing (Tiled Bitmap) to verify data authenticity.
* **Self-Healing Data:** Actively repairs compromised database rows rather than just logging the error.
* **Input Validation:** Built-in `Vaildator.java` ensures clean data entry from the client side.
* **Separation of Concerns:** Strict separation between Admin and Client operations.
---

## 🚀 Future Enhancements

* Web-based dashboard migration (React/Spring Boot).
* Advanced audit logging and analytics dashboard.
* Multi-factor authentication (MFA) for administrative access.
* SMS-based security alerts.
* Support for PostgreSQL and Oracle databases.
---

## 👨‍💻 Developer

**Tanush Balaram Karale**
*Computer Engineering Diploma Student*

---
