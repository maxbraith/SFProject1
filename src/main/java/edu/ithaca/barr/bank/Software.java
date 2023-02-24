package edu.ithaca.barr.bank;

import edu.ithaca.barr.bank.Account.BankAccount;
import edu.ithaca.barr.bank.Account.InsufficientFundsException;

public interface Software { 

    //Class name Bank Teller, has confirmCredentials,checkBalance,withdraw,deposit,transfer,checkHistory
    //Written By Giovanni Cioffi 19-Feb-2023

    /**
     * @post confirm user's credentials are valid
     * @param email - email associated with the account
     * @param password - password associated with the account
     * @return TRUE if credentials are valid. FALSE if not
    */
    boolean confirmCredentials(BankAccount account, String email, String password);

    /** 
     * @post checks balance in account
     * @return balance
     */
    double checkBalance(BankAccount account);

    /**
     * @post withdraws a given amount from account balance
     * @param amount - amount to withdraw from balance
     * @throws edu.ithaca.barr.bank.account.InsufficientFundsException
     * @throws InsufficientBalance if amount>balance
     * @throws InvalidArgumentException if amount is not valid
     * @throws AccountFrozen exception if account is frozen
     */
    void withdraw(BankAccount account, double amount) throws InsufficientFundsException;
    
    /**
     * @post deposits a given amount to account balance
     * @param amount - amount to deposit to balance
     * @throws InvalidArgumentException if amount is not valid
     * @throws AccountFrozen exception if account is frozen
     */
    void deposit(BankAccount account, double amount);

    /**
     * @post transfers a given amount from account to given account
     * @param transferAccount - account to deposit amount into
     * @param amount - amount of money to withdraw and transfer
     * @throws edu.ithaca.barr.bank.account.InsufficientFundsException
     * @throws AccountFrozen exception if account is frozen
     * @throws AccountNotValid exception if passed account is not valid 
     * @throws InvalidArgumentException if amount is not valid
     * @throws InsufficientBalance if amount>balance for either account
     */
    public void transfer(double amount, BankAccount transferAccount1, BankAccount transferAccount2) throws InsufficientFundsException;

    /**
     * @post collects transaction history of an account
     * @return List of previous transactions
     */
    String checkHistory(BankAccount account);
}
