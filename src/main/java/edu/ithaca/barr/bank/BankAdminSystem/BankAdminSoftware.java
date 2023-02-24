package edu.ithaca.barr.bank.bankadminsystem;

import edu.ithaca.barr.bank.account.AbstractAccount;

public class BankAdminSoftware {
    private AbstractAccount[] allAccounts;
    private AbstractAccount[] flaggedAccounts;
    private AbstractAccount[] frozenAccounts;
    
    /**
     * @post returns total money across all accounts
     * @return - double of total money
     */
    public double checkMoneyTotal(){
        return 0;
    }

    /**
     * @post returns array of all accounts flagged as suspiscious
     * @return - array of suspiscious accounts
     */
    public AbstractAccount[] reportOfSuspisciousAccounts(){
        return null;
    }

    /**
     * @post marks an account as frozen
     */
    public void freezeAccount(){
    }

    /**
     * @post unfreezes an account
     */
    public void unfreezeAccount(){
    }
    
}
