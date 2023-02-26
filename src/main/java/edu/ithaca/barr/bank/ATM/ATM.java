package edu.ithaca.barr.bank.atm;

import edu.ithaca.barr.bank.Software;
import edu.ithaca.barr.bank.Account.BankAccount;
import edu.ithaca.barr.bank.Account.InsufficientFundsException;
import edu.ithaca.barr.bank.Account.AbstractAccount;

//Class name ATM, has confirmCredentials,checkBalance,withdraw,deposit,transfer,checkHistory
//Written By Giovanni Cioffi 19-Feb-2023

public class ATM implements Software{
     /**
     * @post confirm user's credentials are valid
     * @param email - email associated with the account
     * @param password - password associated with the account
     * @return TRUE if credentials are valid. FALSE if not
    */
    public boolean confirmCredentials(AbstractAccount account, String email, String password){
        if ((account.getEmail()==email)&&(((BankAccount) account).getPassword()==password))
        return true;
        else{return false;}
    }

    /** 
     * @post checks balance in account
     * @return balance
     */
    public double checkBalance(AbstractAccount account){
        return account.getBalance();
    }

    /**
     * @post withdraws a given amount from account balance
     * @param amount - amount to withdraw from balance
     * @throws InsufficientFundsException if amount>balance
     * @throws InvalidArgumentException if amount is not valid
     * @throws AccountFrozen exception if account is frozen
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw (AbstractAccount account, double amount) throws InsufficientFundsException{
        if (!BankAccount.isAmountValid(amount)){
            throw new IllegalArgumentException("Amount cannot be negative or have more than two numbers after the decimal point");
        } 
        if (amount < account.getBalance()){
            account.withdraw(amount);
        }
        else if (Math.abs(account.getBalance()-amount)<.01){ //if balance and amount are equivalent to 3 significant figures after the decimal point
            account.balance -= amount;
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }


    /**
     * @post increases the balance by amount if amount is non-negative and has two decimal points or less
     * @param amount - dollar/cent value to interact with your bank account - must not be more than two decimal places and has to be positive
     * @throws IllegalArgumentException if amount is negative or amount has more than two decimal points
     * @throws AccountFrozen exception if account is frozen
     */
    public void deposit(AbstractAccount account, double amount) throws IllegalArgumentException{
        if (!BankAccount.isAmountValid(amount)){
            throw new IllegalArgumentException("Amount cannot be negative or have more than two numbers after the decimal point");
        } 
        account.deposit(amount);
    }


    /**
     * @post transfers a given amount from account to given account
     * @param transferAccount - account to deposit amount into
     * @param amount - amount of money to withdraw and transfer
     * @throws AccountFrozen exception if account is frozen
     * @throws AccountNotValid exception if passed account is not valid 
     * @throws InvalidArgumentException if amount is not valid
     * @throws InsufficientBalance if amount>balance for either account
     */
    public void transfer(double amount, AbstractAccount transferAccount1, AbstractAccount transferAccount2) throws InsufficientFundsException{
        transferAccount1.withdraw(amount); //withdraws from current bank
        transferAccount2.deposit(amount); //deposits in passed bank
    }
    
    /**
     * @post collects transaction history of an account
     * @return List of previous transactions
     */
    public String checkHistory(AbstractAccount account){
        return account.historyToString();
    }
}
