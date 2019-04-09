package entities;

import java.util.Date;

/**
 * @author Joachim
 */
public class CreditCard {
    int id;
    Account account;
    String date;
    int pinCode;
    int wrongPinCodeAttempts;
    boolean block = false;
    
    public CreditCard() {
        
    }
    
    public CreditCard(int id, Account account, String date, int pinCode, int wrong, boolean block) {
        this.id = id;
        this.account = account;
        this.date = date;
        this.pinCode = pinCode;
        this.wrongPinCodeAttempts = wrong;
        this.block = block;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public int getWrongPinCodeAttempts() {
        return wrongPinCodeAttempts;
    }

    public void setWrongPinCodeAttempts(int wrongPinCodeAttempts) {
        this.wrongPinCodeAttempts = wrongPinCodeAttempts;
    }
    
    public void addWrongPinCodeAttempts() {
        this.wrongPinCodeAttempts++;
    }
    
    public void resetWrongPinCodeAttempts() {
        this.wrongPinCodeAttempts = 0;
    }

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }
    
    
}
