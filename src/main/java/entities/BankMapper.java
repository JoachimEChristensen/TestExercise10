package entities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 * @author Joachim
 */
public class BankMapper {

    BasicDataSource dataSource;
    Connection conn = null;
    Statement stmt = null;

    public BankMapper() {
    }

    public BankMapper(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setDataSource(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public CreditCard createCreditCard(CreditCard creditCard) throws SQLException {
        conn = dataSource.getConnection();
        String query = "Insert into creditcard VALUES (" + creditCard.getId() + ", " + creditCard.getAccount().getId() + ", '" + creditCard.getDate() + "', '" + creditCard.getPinCode() + "', " + creditCard.getWrongPinCodeAttempts() + ", " + creditCard.isBlock() + ");";
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return creditCard;
    }

    public CreditCard updateCreditCard(CreditCard creditCard) throws SQLException {
        conn = dataSource.getConnection();
        String query = "Update creditcard set account_id = " + creditCard.getAccount().getId() + ", last_used = " + creditCard.getDate() + ", pin_code = '" + creditCard.getPinCode() + "', wrong_pin_code_attempts = " + creditCard.getWrongPinCodeAttempts() + ", blocked = " + creditCard.isBlock() + " where id = " + creditCard.getId() + ";";
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return creditCard;
    }

    public CreditCard getCreditCard(int id) throws SQLException {
        CreditCard card = new CreditCard();
        conn = dataSource.getConnection();
        String query = "select * from creditcard where id = " + id + ";";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                card.setId(rs.getInt("id"));
                card.setAccount(getAccount(rs.getInt("account_id")));
                card.setDate(rs.getString("last_used"));
                card.setPinCode(rs.getInt("pin_code"));
                card.setWrongPinCodeAttempts(rs.getInt("wrong_pin_code_attempts"));
                card.setBlock(rs.getBoolean("blocked"));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return card;
    }

    public List<CreditCard> getCreditCards() throws SQLException {
        List<CreditCard> list = new ArrayList<>();
        CreditCard card = new CreditCard();
        String query = "select * from creditcard;";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                card.setId(rs.getInt("id"));
                card.setAccount(getAccount(rs.getInt("account_id")));
                card.setDate(rs.getString("last_used"));
                card.setPinCode(rs.getInt("pin_code"));
                card.setWrongPinCodeAttempts(rs.getInt("wrong_pin_code_attempts"));
                card.setBlock(rs.getBoolean("blocked"));
                list.add(card);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return list;
    }

    public Account createAccount(Account account) throws SQLException {
        conn = dataSource.getConnection();
        String query = "Insert into account VALUES (" + account.getId() + ", " + account.getBalance() + ");";
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return account;
    }

    public Account updateAccount(Account account) throws SQLException {
        conn = dataSource.getConnection();
        String query = "update account set balance = " + account.getBalance() + " where id = " + account.getId() + ";";
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return account;
    }

    public Account getAccount(int id) throws SQLException {
        Account account = new Account();
        conn = dataSource.getConnection();
        String query = "select * from account where id = " + id + ";";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                account.setId(rs.getInt("id"));
                account.setBalance(rs.getDouble("balance"));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return account;
    }

    public List<Account> getAccounts() throws SQLException {
        List<Account> list = new ArrayList<>();
        Account account = new Account();
        conn = dataSource.getConnection();
        String query = "select * from account;";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                account.setId(rs.getInt("id"));
                account.setBalance(rs.getDouble("balance"));
                list.add(account);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return list;
    }
}
