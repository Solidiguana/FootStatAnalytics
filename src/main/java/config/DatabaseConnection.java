package config;

import UI.ConsoleUI;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;

    private final String URL = "jdbc:postgresql://localhost:5432/footstat_db";
    private final String USER = "postgres";
    private final String PASS = "root";

    private DatabaseConnection() {
        try {
            this.connection = (Connection) DriverManager.getConnection(URL, USER, PASS);
            ConsoleUI.success("Database Connected.");
        } catch (SQLException e) {
            ConsoleUI.error("Connection Failed: " + e.getMessage());
        }
    }

    public static synchronized DatabaseConnection getInstance() {
        try {
            if (instance == null || instance.getConnection() == null || instance.getConnection().isClosed()) {
                instance = new DatabaseConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return instance;
    }

    public Connection getConnection() { return connection; }
}
