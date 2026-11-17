# Digital Evidence Management System

**Object-Oriented Programming Using Java (OOPJ)**

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