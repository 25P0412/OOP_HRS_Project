package oop.project.hrs.ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHelper {
    private static final String URL = "jdbc:sqlite:hotel_database.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void initializeDatabase() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS guests (" +
                "username TEXT PRIMARY KEY, " +
                "password TEXT NOT NULL, " +
                "dob TEXT, " +
                "gender TEXT, " +
                "address TEXT, " +
                "balance REAL DEFAULT 0.0" +
                ");";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
            System.out.println("SQLite Database initialized and table is ready!");
        } catch (SQLException e) {
            System.out.println("Database initialization error: " + e.getMessage());
        }
    }

    public static void loadUsersFromDatabase() {
        String sql = "SELECT * FROM guests";
        try (java.sql.Connection conn = connect();
             java.sql.Statement stmt = conn.createStatement();
             java.sql.ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String user = rs.getString("username");
                String pass = rs.getString("password");
                String dobStr = rs.getString("dob");
                String genderStr = rs.getString("gender");
                String address = rs.getString("address");
                double balance = rs.getDouble("balance");

                oop.project.hrs.backend.GuestAuth.register(
                        user,
                        pass,
                        oop.project.hrs.backend.Gender.valueOf(genderStr.toUpperCase()),
                        java.time.LocalDate.parse(dobStr),
                        balance,
                        address
                );
            }
            System.out.println("Data loaded successfully from SQLite!");
        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
}
