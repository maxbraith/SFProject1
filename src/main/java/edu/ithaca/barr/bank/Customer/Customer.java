package edu.ithaca.barr.bank.customer;

import edu.ithaca.barr.bank.account.AbstractAccount;
import edu.ithaca.barr.bank.account.CheckingAccount;
import edu.ithaca.barr.bank.account.SavingsAccount;


public class Customer {
    private String password;
    private int customerId;
    private AbstractAccount savingsAccount;
    private AbstractAccount checkingAccount;

    public Customer(int customerId, String password){
        this.customerId = customerId;
        this.password = password;
        savingsAccount = null;
        checkingAccount = null;
    }

    /**
     * @param savingsAccountIn - savings account associated with customer
     * @post sets instance varaiable savingsAccount equal to a created savingsAccount
     */
    public void setSavingsAccount(SavingsAccount savingsAccountIn){
    }

    /**
     * @param checkingAccountIn
     * @post sets instance varaiable checkingAccount equals to a created checkingAccount associated with the customer
     */
    public void setCheckingsAccount(CheckingAccount checkingAccountIn){
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


    /**
     * @post returns balance in saving account
     * @return balance of savings account associated with customer
     */
    private double getSaveAccountBalance() {
        return 0;
    }

    /**
     * @post returns balance in savings account
     * @return balance of savings account associated with customer
     */
    private double getCheckingAccountBalance() {
        return 0;
    }

    /**
     * @post withdraws given amount from checkings account
     * @param amount - amount to withdraw
     */
    public void withdrawCheckingAccount(double amount) {
    }

    /**
     * @post withdraws given amount from checkings account
     * @param amount - amount to withdraw
     */
    public void withdrawSavingsAccount(double amount) {
    }

    /**
     * @post deposits given amount into checking account
     * @param amount - amount to deposit
     */
    public void depositCheckingAccount(double amount) {
    }

    /**
     * @post deposits given amount into savings account
     * @param amount - amount to deposit
     */
    public void depositSavingsAccount(double amount) {
    }
}
