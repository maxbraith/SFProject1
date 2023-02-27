package edu.ithaca.barr.bank.bankadminsystem;

import java.util.ArrayList;
import java.util.List;

import edu.ithaca.barr.bank.account.AbstractAccount;
import edu.ithaca.barr.bank.account.Bank;

//Max Braithwaite
public class BankAdminSoftware {
    private int adminID;
    private String password;

    public BankAdminSoftware(int adminID, String password){
        this.adminID = adminID;
        this.password = password;
    }



    /**
     * @post returns total money across all accounts
     * @return - double of total money
     */
    public double checkMoneyTotal(Bank bank){
        double total=0;
        for(int i=0; i<bank.getAllAccounts().size(); i++){
            total += bank.getAllAccounts().get(i).getBalance();
        }
        return total;
    }

    /**
     * @post returns array of all accounts flagged as suspiscious
     * @return - array of suspiscious accounts
     */
    public List<AbstractAccount> reportOfSuspisciousAccounts(Bank bank){
        return bank.getFlaggedAccounts();
    }

    /**
     * @post marks an account as frozen
     */
    public void freezeAccount(AbstractAccount account, Bank bank){
        account.freeze();
        bank.frozenAccounts.add(account);
    }

    /**
     * @post unfreezes an account
     */
    public void unfreezeAccount(AbstractAccount account, Bank bank){
        account.unfreeze();
        bank.frozenAccounts.remove(account);
    }

    public String getPassword(){
        return password;
    }
    
}
