package edu.ithaca.barr.bank;

import java.util.List;

public interface Software {


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
    int checkBalance();

    /**
     * @post withdraws a given amount from account balance
     * @param amount - amount to withdraw from balance
     */
    void withdraw(int amount);
    
    /**
     * @post deposits a given amount to account balance
     * @param amount - amount to deposit to balance
     */
    void deposit(int amount);

    /**
     * @post transfers a given amount from account to given account
     * @param transferAccount - account to deposit amount into
     * @param amount - amount of money to withdraw and transfer
     */
    void transfer(int amount, BankAccount transferAccount);

    /**
     * @post collects transaction history of an account
     * @return List of previous transactions
     */
    List checkHistory();

    
    
}
