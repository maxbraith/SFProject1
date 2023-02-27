package edu.ithaca.barr.bank.account;

//Written by Matthew Weil
public class BankAccount extends AbstractAccount {

    private String email;
    private String password;
    public double balance;

    /**
     * Constructor for BankAccount
     * @throws IllegalArgumentException if email is invalid 
     */
    public BankAccount(String email, String password, double startingBalance){
        //make sure email is valid
        if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
        }else{
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
        //make sure startBalnce is Valid
        if(isAmountValid(startingBalance)){
            this.balance = startingBalance;
        }else{
            throw new IllegalArgumentException("Amount is invalid, cannot create account");
        }
    }

    /**
     * Getter for balance
     * @return the balance of the bank account
     */
    public double getBalance(){
        return balance;
    }

    /**
     * Getter for password
     * @return the password of the bank account
     */
    public String getPassword(){
        return password;
    }

    /**
     * Getter for email
     * @return email
     */
    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     * If the amount is negative:
     * @throws InsufficientFundsException
     * If the amount is larger then balance:
     * @throws IllegalArgumentException
     */
    public void withdraw (double amount) throws InsufficientFundsException{
        if(!isAmountValid(amount)){
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        if (amount <= balance){
            balance -= amount;
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }

    /**
     * Checks to see if an email for the bank account is valid or not
     * @param takes an email
     * @return a boolean that says if an email is valid or not 
     */
    public static boolean isEmailValid(String email){ //this method was made by my partner: Max, the comments are my own though
        if(email.length() < 5){ //checks to make sure email is valid length
            return false;
        }
        else if(email.indexOf('@') <= 0){ //checks to make sure @ is not the first character
            return false;
        }
        else if(email.lastIndexOf('.') >= email.length()-2 || email.indexOf('.') == 0){ //checks to make sure there are at least 2 characters after the . in the domain
            return false;
        }
        else if(Character.isLetter(email.charAt(email.indexOf('@')-1)) == false){ //checks to make sure email has a @ sign
            return false;
        }
        else if(email.charAt(email.indexOf('.')+1) == '.' || email.charAt(email.lastIndexOf('.')-1) == '.'){ //checks to make sure . is not the first nor last character 
            return false;
        }
        else if(email.contains("#")){ //checks to make sure email does not contain #
            return false;
        }
        else if(Character.isLetter(email.charAt(email.length()-1))==false){ //checks to make sure first character is a letter
            return false;
        }
        else if(email.lastIndexOf('.') < email.indexOf('@')){ //checks to make sure . is after the @
            return false;
        }
        else if(Character.isLetter(email.charAt(email.indexOf('.')+1)) == false){ //checks to make sure there is another letter after a .
            return false;
        }
        else if(Character.isLetter(email.charAt(email.indexOf('_')+1))==false){//checks to make sure there is another letter after a _
            return false;
        }
        else if(Character.isLetter(email.charAt(email.indexOf('-')+1))==false){ //checks to make sure there is another letter after a -
            return false;
        }
        else if(email.contains(" ")) {//checks for whitespace in the email
            return false;
        }else{
            return true;
        }
    }

    /**
     * If the amount is positive and has 2 or less decimal places: returns true
     * If the amount is negative or the amount has more than 2 decimal places: returns false
     * @param amount, the amount of money being checked
     * @return boolean of valid or invalid amount
     */
    public static boolean isAmountValid(double amount){
        //Check if amount is less than 0.00
        if(amount < 0.0){
            return false;
        }else{
            //Check to make sure amount has less then 2 decimal places
            String temp = Double.toString(amount); //put into string to check amount of places past decimal point
            int decimalNumber = temp.indexOf('.'); //get the position in the string of .
            if((temp.length()-decimalNumber -1) > 2){ //gets the number of decimal places and checks if more than 2
                return false;
            }else{
                return true;
            }
        }
    }

    /**
     * Adds money to the bank account
     * @param amount, the amount of money to add to the bank account
     * @throws IllegalArgumentException if amount is invalid
     * @throws IllegalAccessException
     */
    public void deposit(double amount) throws IllegalArgumentException{
        if(isAmountValid(amount)){ 
            balance = balance + amount; // if amount is valid, adds it into the account
        }else{
            throw new IllegalArgumentException("Amount is invalid so we can not do this desired deposit into your bank account.");
        }
    }

    /**
     * Transfers money from one account to another account if the transfer amount is valid
     * @param transferBankAccount different bank account to transfer the money to
     * @param amount the amount of money to transfer to the new bank account
     * @throws IllegalArgumentException
     * @throws InsufficientFundsException
     */
    public void transfer(BankAccount transferBankAccount, double amount) throws IllegalArgumentException, InsufficientFundsException{
        if(!isAmountValid(amount)|| transferBankAccount == null){ //Checks to make sure amount is valid and that another bank account exists
            throw new IllegalArgumentException(); 
        }else{
            //gets money from one account and adds it to the other account
            this.withdraw(amount);
            transferBankAccount.deposit(amount);
        }
    }

    @Override
    public void transfer(double amount, AbstractAccount transferee) throws InsufficientFundsException {
    }
}