package za.co.ohlukilebanking.server.repository;

import server.model.Account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountRepository {
    private static final String JDBC_URL = "jdbc:h2:mem:bankdb";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public Account getAccountById(int accountId) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String query = "SELECT * FROM accounts WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, accountId);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Account(rs.getInt("id"), rs.getString("ownerName"), rs.getDouble("balance"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateAccount(Account account) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String query = "UPDATE accounts SET balance = ? WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setDouble(1, account.getBalance());
            statement.setInt(2, account.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
