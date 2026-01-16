# FootStat Analytics

## 1.
**FootStat Analytics** is a centralized backend system designed to solve the problem of inconsistent football match data tracking. Instead of relying on manual spreadsheets, this system provides a structured database to store teams, players, matches, and performance statistics.

**Key Goals:**
*   **Centralization:** Stores all match data in a relational database (PostgreSQL).
*   **Automation:** Automatically calculates player performance ratings based on raw input.
*   **Security:** Enforces Role-Based Access Control (RBAC) to distinguish between Admins, Analysts, and regular Users.

---

## 2.)

The system uses **PostgreSQL** for persistent storage.

### Technical Implementation
*   **JDBC (Java Database Connectivity):** Used for direct SQL execution.
*   **Singleton Pattern:** The `DatabaseConnection` class ensures only **one** active connection instance exists throughout the application lifecycle to manage resources efficiently.
*   **Data Integrity:** Uses Foreign Keys (`team_id`, `match_id`) and SQL Constraints (e.g., `CHECK (minutes <= 120)`) to ensure valid data.

### Schema Overview
The database consists of 5 related tables:
1.  **Users:** Authentication & Role management.
2.  **Teams:** Clubs and Cities.
3.  **Players:** Linked to Teams.
4.  **Matches:** Links Home/Away Teams and Date.
5.  **Player_Stats:** Linked to Matches & Players (Contains the calculated Rating).

---

## 3.

### Folder Hierarchy
```text
src/main/java/
├── config/    
├── controller/     
│   ├── AdminController.java
│   ├── AuthController.java
│   ├── PublicController.java
│   └── StatController.java
├── entity/      
│   ├── BaseEntity.java
│   ├── User.java
│   ├── Player.java
│   └── ...
├── repository/     
│   ├── IRepository.java 
│   ├── PlayerRepository.java
│   └── ...
├── service/           
│   ├── AuthService.java
│   ├── SecurityService.java
│   └── StatService.java
└── Main.java     
