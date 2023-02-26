package edu.ithaca.barr.bank.bankadminsystem;

import java.util.ArrayList;

import edu.ithaca.barr.bank.account.AbstractAccount;

public class BankAdminSoftware {
    public ArrayList<AbstractAccount> allAccounts;
    public ArrayList<AbstractAccount> flaggedAccounts;
    public ArrayList<AbstractAccount> frozenAccounts;
    public  ArrayList<AbstractAccount> suspiciousAccounts;

    /**
     * @post returns total money across all accounts
     * @return - double of total money
     */
    public double checkMoneyTotal(){
        double total=0;
        for(int i=0; i<allAccounts.size(); i++){
            total += allAccounts.get(i).getBalance();
        }
        return total;
    }

    /**
     * @post returns array of all accounts flagged as suspiscious
     * @return - array of suspiscious accounts
     */
    public ArrayList<AbstractAccount> reportOfSuspisciousAccounts(){
        return flaggedAccounts;
    }

    /**
     * @post marks an account as frozen
     */
    public void freezeAccount(AbstractAccount account){
        account.freeze();
        frozenAccounts.add(account);
    }

    /**
     * @post unfreezes an account
     */
    public void unfreezeAccount(AbstractAccount account){
        account.unfreeze();
        frozenAccounts.remove(account);
    }
    
}
