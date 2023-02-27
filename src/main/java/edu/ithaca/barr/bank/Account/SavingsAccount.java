package edu.ithaca.barr.bank.account;

import java.util.ArrayList;

//Written by Matthew Weil
public class SavingsAccount extends AbstractAccount{

    final double initialWithdrawLimit;
    private double remainingWithdraw;
    private double percentInterest;
    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public SavingsAccount(double startingBalance, double withdrawLimit, double percentInterest){
        if(isNumberValid(startingBalance)){
            this.balance = startingBalance;
        }
        else{
            throw new IllegalArgumentException("Starting Balance: " + startingBalance + " is invalid, cannot create account");
        }
        if (isNumberValid(withdrawLimit)){
            this.remainingWithdraw = withdrawLimit;
            this.initialWithdrawLimit = withdrawLimit;
        }
        else{
            throw new IllegalArgumentException("Withdraw Limit: " + withdrawLimit + " is invalid, cannot create account");
        }
        if (isNumberValid(percentInterest)){
            this.percentInterest = percentInterest;
        }else{
            throw new IllegalArgumentException("Interest: " + percentInterest + " is invalid, cannot create account");
        }
        this.history = new ArrayList<String>();
    }
/**
     * @post reduces the balance by @param amount if amount is non-negative, smaller than balance and less than the withdraw limit.
     */
    public void withdraw (double amount) throws InsufficientFundsException{
        if (isNumberValid(amount)) { 
            if (amount <= remainingWithdraw){    
                if (amount <= balance){
                    remainingWithdraw-=amount;
                    balance -= amount;
                    balance = Math.round(balance * 100.0) / 100.0; // Multiply by 100 and round to cut off all decimals past the 
                    appendTransaction(amount, "withdraw");         // hundreths place. Divide by 100 to make sure the number has two decimasl again                       
                }else{                                             
                    throw new InsufficientFundsException("Not enough money in the account.");
                }
            }else{
                throw new IllegalArgumentException("Amount to withdraw is greater than withdraw limit.");
            }
        }else{
            throw new IllegalArgumentException("Amount to withdraw is invalid.");
        }
    }

    /**
     * @post reduces the balance by @param amount if amount is non-negative, smaller than balance and withdrawLimit.
     * Deposits the same amount into @param transferee account.
     */
    public void transfer(double amount, AbstractAccount transferee) throws InsufficientFundsException {
        if (isNumberValid(amount)) { 
            if (amount <= remainingWithdraw){    
                if (amount <= balance){
                    withdraw(amount);
                    transferee.deposit(amount);                       
                }else{                                                              
                    throw new InsufficientFundsException("Not enough money in the account.");
                }
            }else{
                throw new IllegalArgumentException("Amount to withdraw is greater than withdraw limit.");
            }
        }else{
            throw new IllegalArgumentException("Amount to withdraw is invalid.");
        }
    }

    /**
     * @post changes the balance based on the interest and balance of the account
     */
    public void addInterest(){
        double interest = calculateInterest();
        this.deposit(interest);
    }          
    

    public double calculateInterest(){
        double interest = balance * percentInterest/100;
        interest = Math.round(interest * 100.0) / 100.0; // Multiply by 100 and round to cut off all decimals past the
        return interest;                                // hundreths place. Divide by 100 to make sure the number has two decimasl again 
    }
    public double getInterest(){
        return percentInterest;
    }
    
    public double getRemainingWithdraw(){
        return remainingWithdraw;
    }
    public void resetWithdrawLimit(){
        remainingWithdraw = initialWithdrawLimit;
    }
}
