
package entities;

/**
 * @author Joachim
 */
public class Account {
    int id;
    double balance;
    
    public Account() {
        
    }
    
    public Account(int id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    public void deposit(double amount) {
        this.balance = this.balance + amount;
    }
    
    public void withdraw(double amount) {
        this.balance = this.balance - amount;
    }
}
