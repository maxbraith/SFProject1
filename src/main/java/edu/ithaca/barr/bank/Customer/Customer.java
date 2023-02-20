package edu.ithaca.barr.bank.customer;

import edu.ithaca.barr.bank.account.AbstractAccount;


public class Customer {
    private String password;
    private int customerId;
    private AbstractAccount savingsAccount;
    private AbstractAccount checkingAccount;

    public Customer(String password, int customerId){
        this.customerId = customerId;
        this.password = password;
        savingsAccount = null;
        checkingAccount = null;
    }

    /**
     * Returns the Customers total balance
     * @return total amount of money the customer has in their accounts
     */
    public double getBalance(){
        double total = 0;
        try{
            total = total + getCheckingAccountBalance();
        }catch(IllegalArgumentException e){}
        try{
            total = total + getSaveAccountBalance();
        }catch(IllegalArgumentException e){}
        return total;
    }



    private double getSaveAccountBalance() {
        return 0;
    }

    private double getCheckingAccountBalance() {
        return 0;
    }
}
