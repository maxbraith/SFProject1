package edu.ithaca.barr.bank.atm;

import edu.ithaca.barr.bank.account.AbstractAccount;
import edu.ithaca.barr.bank.bankadminsystem.BankAdminSoftware;
import edu.ithaca.barr.bank.customer.Customer;
import edu.ithaca.barr.bank.teller.BankTeller;

public class ATM {

    public ATM(int i, String string) {
    }

    public void addCustomer(Customer intialCustomer) {
    }

    public void createNewAccount(BankTeller intialTeller, Customer intialCustomer, int i, int j, int k, int l) {
    }

    public void addTeller(BankTeller intialTeller) {
    }

    public void addAdmin(ATM intialAdmin) {
    }

    public Object getAccounts() {
        return null;
    }

    public Object getCustomers() {
        return null;
    }

    public void createNewAccount(BankTeller teller, int nextId, String password, int account, double withdrawLimit,
            double interestRate, double startBal) {
    }

    public void createNewAccount(BankTeller teller, Customer customer, int account, double withdrawLimit,
            double interestRate, double startBal) {
    }

    public void addAccount(AbstractAccount newAccount) {
    }

    public BankAdminSoftware adminLogIn(int id, String password) {
        return null;
    }

    public BankTeller tellerLogIn(int id, String password) {
        return null;
    }

    public Customer customerLogIn(int id, String password) {
        return null;
    }
    
}
