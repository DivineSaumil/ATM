package Simulation;
import java.sql.*;
public class Database {
    private Connection conn;

    // Constructor to initialize the database connection
    public Database(String url, String username, String password) throws SQLException {
        conn = DriverManager.getConnection(url, username, password);
        createTable();
    }

    // Method to create the Account table
    private void createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS Account ("
                + "account_no INT(11) PRIMARY KEY,"
                + "name VARCHAR(50),"
                + "balance FLOAT,"
                + "pin INT(4),"
                + "info VARCHAR(100)"
                + ")";
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
    }

    // Method to add a new record to the Account table
    public void addRecord(int account_no, String name, float balance, int pin, String info) throws SQLException {
        String sql = "INSERT INTO Account (account_no, name, balance, pin, info) "
                + "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, account_no);
        pstmt.setString(2, name);
        pstmt.setFloat(3, balance);
        pstmt.setInt(4, pin);
        pstmt.setString(5, info);
        pstmt.executeUpdate();
    }

    // Method to delete a record from the Account table by account_no
    public void deleteRecord(int account_no) throws SQLException {
        String sql = "DELETE FROM Account WHERE account_no=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, account_no);
        pstmt.executeUpdate();
    }
    //Method to retrieve data from database
    public ResultSet getRecord(int account_no, int pin) throws SQLException {
        String sql = "SELECT * FROM Account WHERE account_no=? AND pin=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, account_no);
        pstmt.setInt(2, pin);
        ResultSet rs = pstmt.executeQuery();
        return rs;
    }
    

    // Method to update a record in the Account table by account_no
    public void updateRecord(int account_no, String name, float balance, int pin, String info) throws SQLException {
        String sql = "UPDATE Account SET name=?, balance=?, pin=?, info=? WHERE account_no=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.setFloat(2, balance);
        pstmt.setInt(3, pin);
        pstmt.setString(4, info);
        pstmt.setInt(5, account_no);
        pstmt.executeUpdate();
    }
}
