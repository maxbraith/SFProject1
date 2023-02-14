package edu.ithaca.barr.bank;

import java.util.Queue;

public interface Software { //should this be a superclass instead?


    /**
     * @post confirm user's credentials are valid
     * @param email - email associated with the account
     * @param password - password associated with the account
     * @return TRUE if credentials are valid. FALSE if not
    */
    boolean confirmCredentials(String email, String password);

    /** 
     * @post checks balance in account
     * @return balance
     */
    double checkBalance();

    /**
     * @post withdraws a given amount from account balance
     * @param amount - amount to withdraw from balance
     * @throws InsufficientBalance if amount>balance
     * @throws InvalidArgumentException if amount is not valid
     * @throws AccountFrozen exception if account is frozen
     */
    void withdraw(double amount);
    
    /**
     * @post deposits a given amount to account balance
     * @param amount - amount to deposit to balance
     * @throws InvalidArgumentException if amount is not valid
     * @throws AccountFrozen exception if account is frozen
     */
    void deposit(double amount);

    /**
     * @post transfers a given amount from account to given account
     * @param transferAccount - account to deposit amount into
     * @param amount - amount of money to withdraw and transfer
     * @throws AccountFrozen exception if account is frozen
     * @throws AccountNotValid exception if passed account is not valid 
     * @throws InvalidArgumentException if amount is not valid
     * @throws InsufficientBalance if amount>balance for either account
     */
    void transfer(double amount, BankAccount transferAccount);

    /**
     * @post collects transaction history of an account
     * @return List of previous transactions
     */
    Queue<String> checkHistory();
}
