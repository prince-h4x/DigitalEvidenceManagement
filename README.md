# Digital Evidence Management System

**Object-Oriented Programming Using Java (OOPJ)**




<div align="center">

# ⚡ THE TEAM BEHIND THE SYSTEM

### DIGITAL EVIDENCE MANAGEMENT SYSTEM
**GROUP 09**

*Three minds. One vision. One powerful system.*

</div>

---

## 👨‍💻 MEET THE DEVELOPERS

<table align="center">
<tr>
<td align="center" width="33%">

### 👑 PRINCE BHARDWAJ

<a href="https://github.com/prince-h4x">
<img src="https://github.com/prince-h4x.png" width="120" alt="Prince"/>
</a>

**BACKEND DEVELOPER**

`Backend & Database`

Java • Spring Boot • Database Integration

<a href="https://github.com/prince-h4x">
<img src="https://img.shields.io/badge/GitHub-@prince--h4x-181717?style=for-the-badge&logo=github" />
</a>

</td>
<td align="center" width="33%">

### 🚀 ROHAN

<a href="https://github.com/rohannnbhandari39">
<img src="https://github.com/rohannnbhandari39.png" width="120" alt="Rohan"/>
</a>

**PROJECT LEAD**

`Frontend Developer`

UI / UX • HTML • CSS • Thymeleaf

<a href="https://github.com/rohannnbhandari39">
<img src="https://img.shields.io/badge/GitHub-@rohannnbhandari39-181717?style=for-the-badge&logo=github" />
</a>

</td>
<td align="center" width="33%">

### 🧪 SUJAL

<a href="https://github.com/sc2534chauhan-create">
<img src="https://github.com/sc2534chauhan-create.png" width="120" alt="Sujal"/>
</a>

**QA & DOCUMENTATION LEAD**

`Testing & Quality Assurance`

Testing • Debugging • Documentation

<a href="https://github.com/sc2534chauhan-create">
<img src="https://img.shields.io/badge/GitHub-@sc2534chauhan--create-181717?style=for-the-badge&logo=github" />
</a>

</td>
</tr>
</table>

---

<div align="center">

### 🏆 GROUP 09

**Built with teamwork, dedication & innovation.**

*Academic Project • Digital Evidence Management System*

</div>




## 1. Project Overview

The Digital Evidence Management System is a Java-based desktop application designed to manage digital evidence associated with investigation cases. It provides a centralized interface for registering cases, adding evidence, maintaining chain-of-custody records, searching evidence, and generating case reports.

## 2. Features

- **Case Registration:** Register investigation cases with details such as title, description, investigator, date, and status.
- **Evidence Entry:** Add digital evidence and classify it as Document, Image, or Log.
- **Chain of Custody:** Record evidence-handling activities and maintain custody history.
- **Evidence Search:** Search evidence using its unique ID.
- **Evidence Management:** Update and delete evidence records.
- **Case Reports:** View case details, associated evidence, and custody records.
- **Case Management:** Update case status or delete cases.
- **Database Integration:** Store records persistently using MySQL.

## 3. Technologies Used

- Java
- Java Swing (GUI)
- JDBC (Java Database Connectivity)
- MySQL
- MySQL Connector/J
- VS Code

## 4. Project Modules

| Unit | Concepts Implemented |
|---|---|
| Unit 1 | Classes, objects, String methods, File handling |
| Unit 2 | Inheritance, abstract classes, interfaces, packages |
| Unit 3 | User-defined exception handling |
| Unit 4 | JDBC, PreparedStatement, CRUD operations |
| Unit 5 | Java Swing, layouts, event handling |

## 5. Database

Database name: `digital_evidence_db`

Tables:
- `cases`
- `evidence`
- `custody`

The database uses primary keys, foreign keys, and cascading deletion to maintain relationships between cases, evidence, and custody records.

## 6. How to Run

### Prerequisites
- Java JDK
- MySQL Server
- MySQL Connector/J

### Setup
1. Create the MySQL database named `digital_evidence_db`.
2. Create the required tables: `cases`, `evidence`, and `custody`.
3. Place the MySQL Connector/J JAR inside the project's `lib` folder.
4. Verify the database connection settings in `src/evidence/database/DBConnection.java`.

### Compile

Run these commands from the project root:

```bash
mkdir -p out
javac -cp "lib/mysql-connector-j-26.10.0.jar" -d out src/evidence/core/*.java src/evidence/database/*.java src/evidence/dao/*.java src/evidence/ui/*.java src/Main.java
```

### Run

```bash
java -cp "out:lib/mysql-connector-j-26.10.0.jar" Main
```

## 7. Project Structure

```text
DigitalEvidenceManagement/
├── lib/
│   └── mysql-connector-j-26.10.0.jar
├── src/
│   ├── Main.java
│   └── evidence/
│       ├── core/
│       ├── database/
│       ├── dao/
│       └── ui/
├── out/
└── README.md
```

## 8. Conclusion

The Digital Evidence Management System demonstrates the practical application of object-oriented programming, database connectivity, exception handling, and graphical user interface development in Java. It provides a structured approach to managing investigation cases and their associated digital evidence.
