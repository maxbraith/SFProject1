package edu.ithaca.barr.bank.account;

import java.util.ArrayList;

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

    @Override
    public void withdraw(double Amount) throws InsufficientFundsException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void transfer(double amount, AbstractAccount transferee) throws InsufficientFundsException {
        // TODO Auto-generated method stub
        
    }


    public void addInterest(){
    }          
    
    public double calculateInterest(){
        return 1.0;
    }
    public double getInterest(){
        return percentInterest;
    }
    
    public double getRemainingWithdraw(){
        return remainingWithdraw;
    }

    public void resetWithdrawLimit(){
    }
}
