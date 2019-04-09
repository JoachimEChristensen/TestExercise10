/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import entities.*;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Joachim
 */
public class TestMain {

    BasicDataSource dataSource = new BasicDataSource();
    BankMapper bm = new BankMapper();

    public TestMain() throws SQLException {
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setUrl("jdbc:mysql://localhost/dependency?useSSL=false");
        bm.setDataSource(dataSource);
    }

    @Test
    public void One() throws SQLException {
        bm.createAccount(new Account(11, 55.55));
        CreditCard cc = bm.createCreditCard(new CreditCard(11, new Account(11, 55.55), "1996-9-2 00:00:00", 2222, 0, false));
        assertEquals(cc, bm.getCreditCard(11));
    }

    @Test
    public void Two() throws SQLException {
        bm.createAccount(new Account(12, 55.55));
        CreditCard cc = bm.createCreditCard(new CreditCard(12, new Account(12, 55.55), "1996-9-2 00:00:00", 2222, 0, false));
        assertFalse(cc == bm.getCreditCard(11));
    }

    @Test
    public void Three() throws SQLException {
        bm.createAccount(new Account(13, 55.55));
        CreditCard cc = bm.createCreditCard(new CreditCard(13, new Account(13, 55.55), "1996-9-2 00:00:00", 2222, 0, false));
        assertTrue(bm.getAccounts().size() == 13);
    }

    @Test
    public void Four() throws SQLException {
        bm.createAccount(new Account(14, 55.55));
        CreditCard cc = bm.createCreditCard(new CreditCard(14, new Account(14, 55.55), "1996-9-2 00:00:00", 2222, 0, false));
        assertFalse(bm.getCreditCards().size() == 0);
    }

    @Test
    public void Five() throws SQLException {
        bm.createAccount(new Account(15, 55.55));
        CreditCard cc = bm.createCreditCard(new CreditCard(15, new Account(15, 55.55), "1996-9-2 00:00:00", 2222, 0, false));
        assertNotSame(bm.getCreditCards(), bm.getAccounts());
    }
}
