package edu.ithaca.barr.bank.customer;

import edu.ithaca.barr.bank.account.CheckingAccount;
import edu.ithaca.barr.bank.account.InsufficientFundsException;
import edu.ithaca.barr.bank.account.SavingsAccount;

//Max Braithwaite

public class Customer {
    private String password;
    private int customerId;
    private SavingsAccount savingsAccount;
    private CheckingAccount checkingAccount;

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
        savingsAccount = savingsAccountIn;
    }

    /**
     * @param checkingAccountIn
     * @post sets instance varaiable checkingAccount equals to a created checkingAccount associated with the customer
     */
    public void setCheckingAccount(CheckingAccount checkingAccountIn){
        checkingAccount = checkingAccountIn;
    }

    /**
     * @post returns the savings account associated with the customer
     * @return SavingsAccount object
     */
    public SavingsAccount getSavingsAccount(){
        return savingsAccount;
    }

    /**
     * @post returns the checking account associated with the customer
     * @return checking account object
     */
    public CheckingAccount getCheckingAccount(){
        return checkingAccount;
    }
    
    /**
     * Returns the Customers total balance
     * @return total amount of money the customer has in their accounts
     */
    public double getBalance(){
        double total = 0.0;

        if(checkingAccount != null){
            try{
                total = total + checkingAccount.getBalance();
            }catch(IllegalArgumentException e){}
        }
        if(savingsAccount != null){
            try{
                total = total + savingsAccount.getBalance();
            }catch(IllegalArgumentException e){}
        }
        return total;
    }

    /**
     * @post withdraws given amount from checkings account
     * @param amount - amount to withdraw
     * @throws edu.ithaca.barr.bank.account.InsufficientFundsException
     */
    public void withdrawCheckingAccount(double amount) throws edu.ithaca.barr.bank.account.InsufficientFundsException {
        checkingAccount.withdraw(amount);
    }

    /**
     * @post withdraws given amount from checkings account
     * @param amount - amount to withdraw
     * @throws edu.ithaca.barr.bank.account.InsufficientFundsException
     * @throws InsufficientFundsException 
     */
    public void withdrawSavingsAccount(double amount) throws edu.ithaca.barr.bank.account.InsufficientFundsException {
        savingsAccount.withdraw(amount);
    }

    /**
     * @post deposits given amount into checking account
     * @param amount - amount to deposit
     */
    public void depositCheckingAccount(double amount) {
        checkingAccount.deposit(amount);
    }

    /**
     * @post deposits given amount into savings account
     * @param amount - amount to deposit
     */
    public void depositSavingsAccount(double amount) {
        savingsAccount.deposit(amount);
    }

    /** 
    * @return customer ID number
    */
    public int getid(){
        return customerId;
    }

    /** 
    * @return customer password 
    */
    public String getPassword(){
        return password;
    }

    /**
     * @param amount is amount to transfer
     * @param customerID is the customer to transfer to
     * @throws InsufficientFundsException
     */
    public void transferSavingsAccount(double amount, Customer customer) throws InsufficientFundsException{
        if (savingsAccount != null){
            savingsAccount.transfer(amount, customer.getSavingsAccount());
        }else{
            throw new IllegalArgumentException("This account does not exist");
        }
    }
    
    /**
     * @param amount is amount to transfer
     * @param customerID is the customer to transfer to
     * @throws InsufficientFundsException
     */
    public void transferCheckingAccount(double amount, Customer customer) throws InsufficientFundsException{
        if (checkingAccount != null){
            checkingAccount.transfer(amount, customer.getCheckingAccount());
        }else{
            throw new IllegalArgumentException("This account does not exist");
        }
    }
}
