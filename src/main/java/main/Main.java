package main;

import entities.*;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 * @author Joachim
 */
public class Main {

    public static void main(String[] args) throws Exception {

        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setUrl("jdbc:mysql://localhost/dependency?useSSL=false");
        
        BankMapper bm = new BankMapper();
        bm.setDataSource(dataSource);
        
        bm.createAccount(new Account(10, 55.55));
        bm.createCreditCard(new CreditCard(10, new Account(10, 55.55), "1996-9-2 00:00:00", 2222, 0, false));
        System.out.println(bm.getAccount(10).getBalance());
    }
}
