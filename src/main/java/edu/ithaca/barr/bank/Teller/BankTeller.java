package edu.ithaca.barr.bank.teller;

import java.util.Queue;

import edu.ithaca.barr.bank.Software;
import edu.ithaca.barr.bank.account.BankAccount;

public class BankTeller implements Software{
    
    /**
     * @post confirm user's credentials are valid
     * @param email - email associated with the account
     * @param password - password associated with the account
     * @return TRUE if credentials are valid. FALSE if not
    */
    public boolean confirmCredentials(String email, String password){
        return false;
    }

    @Override
    public double checkBalance() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void withdraw(double amount) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deposit(double amount) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void transfer(double amount, BankAccount transferAccount) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Queue<String> checkHistory() {
        // TODO Auto-generated method stub
        return null;
    }


}
