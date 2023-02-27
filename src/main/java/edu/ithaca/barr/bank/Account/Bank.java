package edu.ithaca.barr.bank.account;

import java.util.ArrayList;
import java.util.List;

import edu.ithaca.barr.bank.teller.BankTeller;
import edu.ithaca.barr.bank.bankadminsystem.BankAdminSoftware;
import edu.ithaca.barr.bank.customer.Customer;

public class Bank {

    public List<AbstractAccount> flaggedAccounts;
    public List<AbstractAccount> frozenAccounts;

    public List<AbstractAccount> accounts;
    private List<BankTeller> tellers;
    private List<BankAdminSoftware> admins;
    private List<Customer> customers;

    public Bank(){
        accounts = new ArrayList<>();
        tellers = new ArrayList<>();
        admins = new ArrayList<>();
        customers = new ArrayList<>();
        flaggedAccounts = new ArrayList<>();
        frozenAccounts = new ArrayList<>();
    }

        /**
     * @post returns total money across all accounts
     * @return - double of total money
     */
    public double checkMoneyTotal(){
        double total=0;
        for(int i=0; i<accounts.size(); i++){
            total += accounts.get(i).getBalance();
        }
        return total;
    }

    /**
     * login a customer based off of their id and password
     * @param id customer id
     * @param password customer password
     * @return the customer that was logged in
     */
    public Customer customerLogIn(int id, String password){
        Customer customer;
        try{
            customer = customers.get(id);
        }
        catch(IndexOutOfBoundsException e){
            return null;
        }
        if (customer.getPassword().equals(password)){
            return customer;
        }
        else{
            return null;
        }

    }

    /**
     * Login a teller based off of their id and password
     * @param id teller id
     * @param password teller password
     * @return the teller that was logged in
     */
    public BankTeller tellerLogIn(int id, String password){
        BankTeller teller;
        try{
            teller = tellers.get(id);
        }
        catch(IndexOutOfBoundsException e){
            return null;
        }
        if (teller.getPassword().equals(password)){
            return teller;
        }
        else{
            return null;
        }
    }

    /**
     * login a bank admin based off of their id and password
     * @param id id to admin
     * @param password password
     * @returnthe admin that was logged in
     */
    public BankAdminSoftware adminLogIn(int id, String password){
        BankAdminSoftware admin;
        try{
            admin = admins.get(id);
        }
        catch(IndexOutOfBoundsException e){
            return null;
        }
        if (admin.getPassword().equals(password)){
            return admin;
        }
        else{
            return null;
        }
    }

    /**
     * Adds an account to the list of accounts
     * @param account account to add
     */
    public void addAccount(AbstractAccount account){
        accounts.add(account);
    }

    /**
     * adds a teller to the list of tellers
     * @param teller teller to add
     */
    public void addTeller(BankTeller teller){
        tellers.add(teller);
    }

    /**
     * adds an admin to the list of admins
     * @param admin admin to add
     */
    public void addAdmin(BankAdminSoftware admin){
        admins.add(admin);
    }

    /**
     * adds a customer to the list of customers
     * @param customer customer to add
     */
    public void addCustomer(Customer customer){
        customers.add(customer);
    }


    /**
     * gets the list of tellers
     * @return list of tellers
     */
    public List<BankTeller> getTellers(){
        return tellers;
    }

    /**
     * gets the list of admins
     * @return list of admins
     */
    public List<BankAdminSoftware> getAdmins(){
        return admins;
    }

    /**
     * gets the list of customers
     * @return list of customers
     */
    public List<Customer> getCustomers(){
        return customers;
    }

 /**
     * Interfaces with bank teller class to make a new account and adds it to a customer and the account list in the bank
     * Overloaded with another method that creates a new customer to attach the account to as well
     * @param teller teller which will be adding the account
     * @param existCustomer the customer to attach the account to
     * @param accountType the type of account to make
     * @param withdrawLimit the withdraw limit if a savings account is chosen
     * @param percentInt the percent interest if a savings account is chosen
     * @param startBal the starting balance in the account
     */
    public void createNewAccount(BankTeller teller, Customer existCustomer, int accountType, double withdrawLimit, double percentInt, double startBal){
        AbstractAccount account = (AbstractAccount) teller.createAccount(existCustomer, accountType, withdrawLimit, percentInt, startBal);
        accounts.add(account);
    }

    /**
     * Interfaces with bank teller class to make a new account and adds it to a customer and the account list in the bank
     * Overloaded with another method that takes a customer object
     * @param teller teller which will be adding the account
     * @param existCustomer the customer to attach the account to
     * @param accountType the type of account to make
     * @param withdrawLimit the withdraw limit if a savings account is chosen
     * @param percentInt the percent interest if a savings account is chosen
     * @param startBal the starting balance in the account
     */
    public void createNewAccount(BankTeller teller, int customerId, String password, int accountType, double withdrawLimit, double percentInt, double startBal){
        try{
            Customer customer = customers.get(customerId);
            if (customer == null){
                throw new IndexOutOfBoundsException();
            }
            createNewAccount(teller, customer, accountType, withdrawLimit, percentInt, startBal);
        }
        catch(IndexOutOfBoundsException e){
            Customer customer = teller.createAccount(customerId, password, accountType, withdrawLimit, percentInt, startBal);
            addCustomer(customer);
            if (accountType == 0){
                accounts.add((AbstractAccount) customer.getCheckingAccount());
            }
            else if (accountType == 1){
                accounts.add((AbstractAccount) customer.getSavingsAccount());
            }
            else{
                accounts.add((AbstractAccount) customer.getCheckingAccount());
                accounts.add((AbstractAccount) customer.getSavingsAccount());
            }
        }
        
    }

    /**
     * @return List of accounts
     */
    public List<AbstractAccount> getAllAccounts(){
        return accounts;
    }

    /**
     * @return List of suspiscious accounts
     */
    public List<AbstractAccount> getFlaggedAccounts(){
        return flaggedAccounts;
    }

    /**
     * @return List of frozen accounts
     */
    public List<AbstractAccount> getFrozenAccounts(){
        return frozenAccounts;
    }

}
