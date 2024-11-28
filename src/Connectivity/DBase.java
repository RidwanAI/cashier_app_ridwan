package Connectivity;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBase {
    public static final String URL = "jdbc:mysql://localhost:3306/db_warung";
    public static final String USER = "root";
    public static final String PASS = "";
    public static Connection connection = null;
    
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASS);
            }
            return connection;
        } catch (Exception e) {
            System.out.println("Koneksi Gagal: " + e.getMessage());
            return null;
        }
    }
    
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (Exception e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }
}