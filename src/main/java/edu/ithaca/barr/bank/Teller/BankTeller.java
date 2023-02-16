package edu.ithaca.barr.bank.Teller;

import java.util.Queue;

import edu.ithaca.barr.bank.Software;
import edu.ithaca.barr.bank.Account.BankAccount;
import edu.ithaca.barr.bank.Account.InsufficientFundsException;

public class BankTeller implements Software{
    
    /**
     * @post confirm user's credentials are valid
     * @param email - email associated with the account
     * @param password - password associated with the account
     * @return TRUE if credentials are valid. FALSE if not
    */
    public boolean confirmCredentials(BankAccount account, String email, String password){
        if ((account.getEmail()==email)&&(account.getPassword()==password))
        return true;
        else{return false;}
    }

    /** 
     * @post checks balance in account
     * @return balance
     */
    public double checkBalance(BankAccount account){
        return account.getBalance();
    }

    /**
     * @post withdraws a given amount from account balance
     * @param amount - amount to withdraw from balance
     * @throws InsufficientBalance if amount>balance
     * @throws InvalidArgumentException if amount is not valid
     * @throws AccountFrozen exception if account is frozen
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw (double amount) throws InsufficientFundsException{
        if (!isAmountValid(amount)){
            throw new IllegalArgumentException("Amount cannot be negative or have more than two numbers after the decimal point");
        } 
        if (amount < balance){
            balance -= amount;
        }
        else if (Math.abs(balance-amount)<.01){ //if balance and amount are equivalent to 3 significant figures after the decimal point
            balance -= amount;
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }
    
    /**
     * @post deposits a given amount to account balance
     * @param amount - amount to deposit to balance
     * @throws InvalidArgumentException if amount is not valid
     * @throws AccountFrozen exception if account is frozen
     */
    /**
     * @post increases the balance by amount if amount is non-negative and has two decimal points or less
     * @param amount - dollar/cent value to interact with your bank account - must not be more than two decimal places and has to be positive
     * @throws IllegalArgumentException if amount is negative or amount has more than two decimal points
     */
    public void deposit(double amount) throws IllegalArgumentException{
        if (!isAmountValid(amount)){
            throw new IllegalArgumentException("Amount cannot be negative or have more than two numbers after the decimal point");
        } 
        balance += amount;
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
    public void transfer(double amount, BankAccount transferAccount1, BankAccount transferAccount2) throws InsufficientFundsException{
        transferAccount1.withdraw(amount); //withdraws from current bank
        transferAccount2.deposit(amount); //deposits in passed bank
    }

    /**
     * @post collects transaction history of an account
     * @return List of previous transactions
     */
    public Queue<String> checkHistory(){
        return null;
    }




    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid 
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
            this.email = email;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
        if (isAmountValid(startingBalance)){
            this.balance=startingBalance;
        }
        else {
            throw new IllegalArgumentException("Balance: " + balance + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }


    public static boolean isEmailValid(String email){

        //prerequisites for email
        if (email.length()<=5) {
            return false; //min required # of characters for email
        }
        if (email.lastIndexOf(".")==-1) {
            return false; //ensuring there is a . in the email
        }
        if (email.indexOf("@")==-1) {
            return false;//ensuring there is a @ in the email
        }
        if (!Character.isLetterOrDigit(email.charAt(email.indexOf("@")-1))){
            return false; //ensuring that there is a letter or number before the @ symbol
        }
        int i =0; //index to be updated
        if (!(Character.isLetterOrDigit(email.charAt(i)))){
            return false; //ensuring first value is a letter or digit
        } 

        //username before the @ symbol section
        while (i<email.indexOf("@")){ //shortened if statements below as per code review and commented more
            if (!Character.isLetterOrDigit(email.charAt(i))){ //if char is not a letter or digit
                if (('-' == (email.charAt(i))) || ('_' == email.charAt(i)) || ('.' == (email.charAt(i)))){ //allowed special characters
                    if (!(Character.isLetterOrDigit(email.charAt(i+1)))){ //ensuring index after is a letter or digit
                        return false;
                    }
                }
                else{
                    return false;
                }
            }
            i++; 
        }
        i++; //to go past @ symbol before next loop

        //domain area -- before the .com
        while (i<email.lastIndexOf(".")){ //shortened if statements below as per code review and commented more
            if (!Character.isLetterOrDigit(email.charAt(i))){ //if current character is not letter or digit
                if ('-' == (email.charAt(i))){  //dash is allowed
                    if (!(Character.isLetterOrDigit(email.charAt(i+1)))){ //as long as there is a letter or digit after it
                        return false;
                    } 
                }
                else{
                    return false;
                }
            }
            i++;
        }
        i++; //make i the index of the .
        if ((email.length()-i)<=1){ //checks there is two or more letters after the period for the .com area
            return false;
        }
        else{
            return true; //email is valid!
        }
    }

    /*
     * @return true or false based on whether or not the amount is valid
     * @param amount - dollar/cent value to interact with your bank account - must not be more than two decimal places and has to be positive
     */
    public static boolean isAmountValid(double amount){
        if (amount<0){ //cant be negative
            return false;
        }
        String amountTest = Double.toString(amount); //turns to a string to test
        if (amountTest.indexOf(".")!=-1){ //if there is a decimal
            if ((amountTest.length()-1)-(amountTest.indexOf("."))>2){ //there have to be only two significant figures after
                return false;
            }
            else{
                return true;
            }
        }
        else{ //if there is no decimal at all
            return true;
        }
        

    }
}
