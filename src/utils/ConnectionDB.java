package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres?curr";
    private static final String USER = "postgres";
    private static final String PASSWORD = "23122004";

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            try {
                return DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void main(String[] args) throws SQLException {
        Connection conn = getConnection();
        System.out.println("Conn : "+conn);
        System.out.println("status :"+conn.isClosed());
        closeConnection(conn);
        System.out.println("status : "+conn.isClosed());
    }
}