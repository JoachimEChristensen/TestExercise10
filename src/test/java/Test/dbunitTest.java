/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import entities.*;
import java.io.FileInputStream;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Joachim
 */
public class dbunitTest {

    public dbunitTest() {
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "com.mysql.jdbc.Driver");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:mysql://localhost/dependency?useSSL=false");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "root");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "root");
    }

    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(new FileInputStream("full.xml"));
    }

    private void cleanlyInsertDataset(IDataSet dataSet) throws Exception {
        IDatabaseTester databaseTester = new JdbcDatabaseTester(
                "com.mysql.jdbc.Driver", "jdbc:mysql://localhost/dependency?useSSL=false", "root", "root");
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
    }

    @Before
    public void importDataSet() throws Exception {
        IDataSet dataSet = getDataSet();
        cleanlyInsertDataset(dataSet);
    }

    @Test
    public void get() throws SQLException {
        BankMapper bm = new BankMapper(dataSource());
        CreditCard cc = bm.getCreditCard(3);
        Account a = bm.getAccount(3);
        
        assertTrue(cc.getId() == new CreditCard(3, new Account(5, 0.00), "2000-1-30 00:00:00", 1111, 0, false).getId());
        assertTrue(a.getId() == new Account(3, 51324.11).getId());
    }
    
    @Test
    public void cannotFind() throws SQLException {
        BankMapper bm = new BankMapper(dataSource());
        
        CreditCard cc = bm.getCreditCard(99);
        Account a = bm.getAccount(99);
        
        assertEquals(cc.getId(), 0);
        assertEquals(a.getId(), 0);
    }
    
    private BasicDataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setUrl("jdbc:mysql://localhost/dependency?useSSL=false");
        return dataSource;
    }

}
