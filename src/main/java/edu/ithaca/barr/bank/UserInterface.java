package edu.ithaca.barr.bank;

import java.util.Scanner;

import edu.ithaca.barr.bank.account.AbstractAccount;
import edu.ithaca.barr.bank.account.Bank;
import edu.ithaca.barr.bank.bankadminsystem.BankAdminSoftware;
import edu.ithaca.barr.bank.customer.Customer;
import edu.ithaca.barr.bank.teller.BankTeller;

public class UserInterface {

    //initial objects needed
    private static Scanner in = new Scanner(System.in);
    private static Bank bank = new Bank();
    private static int nextId = 0; //for customer Id's

    private static boolean loginState(){
        boolean login = false;
        boolean done = false;
        while (!login){
            System.out.println("Are you a customer, teller, or admin, or exit (0, 1, 2, 3)?");
            try{
                int response = in.nextInt();
                if (response > 3 || response < 0){
                    throw new Exception();
                }
                if (response == 3){
                    done = true;
                    login = true;
                }
                else{
                    System.out.println("Please enter your userId:");
                    try{
                        int id = in.nextInt();
                        System.out.println("Please enter your password:");
                        try{
                            String password = in.next();
                            if (response == 0){
                                try{
                                    Customer customer = bank.customerLogIn(id, password);
                                    if (customer == null){
                                        throw new Exception();
                                    }
                                    else{
                                        customerState(customer);
                                        login = true;
                                    }
                                }
                                catch(Exception e){
                                    System.out.println("I am sorry, that is not a valid login.");
                                }
                            }
                            else if (response == 1){
                                try{
                                    BankTeller teller = bank.tellerLogIn(id, password);
                                    if (teller == null){
                                        throw new Exception();
                                    }
                                    else{
                                        tellerState(teller);
                                        login = true;
                                    }
                                }
                                catch(Exception e){
                                    System.out.println("I am sorry, that is not a valid login.");
                                }
                            }
                            else{
                                try{
                                    BankAdminSoftware admin = bank.adminLogIn(id, password);
                                    if (admin == null){
                                        throw new Exception();
                                    }
                                    else{
                                        adminState(admin);
                                        login = true;
                                    }
                                }
                                catch(Exception e){
                                    System.out.println("I am sorry, that is not a valid login.");
                                }
                            }
                        }
                        catch(Exception e){
                            System.out.println("That is not a valid password.");
                        }
                        
                    }
                    catch(Exception e){
                        System.out.println("That is not a valid id.");
                    }
                }
            }
            catch(Exception e){
                System.out.println("That is not a valid input.");
            }
        }
        return done;
    }

    private static void customerState(Customer customer, int choice){
        if (choice == 0){
            System.out.println(customer.getBalance());
        }
        else if (choice == 1){
            System.out.println("Which account would you like to deposit into? (0 for savings, 1 for checking)");
            try{
                int account = in.nextInt();
                System.out.println("How much would you like to deposit?");
                try{
                    double amount = in.nextDouble();
                    if (account == 0){
                        customer.depositSavingsAccount(amount);
                    }
                    else if (account == 1){
                        customer.depositCheckingAccount(amount);
                    }
                    else{
                        throw new Exception();
                    }
                }
                catch(Exception e){
                    System.out.println("Invalid Amount");
                }
            }
            catch(Exception e){
                System.out.println("Invalid account");
            }
        }
        else if (choice == 2){
            System.out.println("Which account would you like to withdraw from? (0 for savings, 1 for checkings)");
            try{
                int account = in.nextInt();
                System.out.println("How much would you like to withdraw?");
                try{
                    double amount = in.nextDouble();
                    if (account == 0){
                        customer.withdrawSavingsAccount(amount);
                    }
                    else if (account == 1){
                        customer.withdrawCheckingAccount(amount);
                    }
                    else{
                        throw new Exception();
                    }
                }
                catch(Exception e){
                    System.out.println("Invalid Amount");
                }
            }
            catch(Exception e){
                System.out.println("Invalid account");
            }
        }
        else if (choice == 3){
            System.out.println("Which account would you like to transfer from? (0 for savings, 1 for checkings)");
            try{
                int account = in.nextInt();
                System.out.println("How much would you like to trasfer?");
                try{
                    double amount = in.nextDouble();
                    System.out.println("Enter the id of the customer you wish to transfer the money to");
                    try{
                        int transferId = in.nextInt();
                        if (account == 0){
                            customer.transferSavingsAccount(amount, bank.getCustomers().get(transferId));
                        }
                        else if (account == 1){
                            customer.transferCheckingAccount(amount, bank.getCustomers().get(transferId));
                        }
                        else{
                            throw new Exception();
                        }
                    }
                    catch(Exception e){
                            System.out.println("Invalid id or invalid ammount");
                    }
                }
                catch(Exception e){
                    System.out.println("Invalid amount");
                }
            }
            catch(Exception e){
                System.out.println("Invalid account");
            }
        }
    }

    private static void customerState(Customer customer){
        boolean done = false;
        while(!done){
            System.out.println("Options:\n0\tCheck Balance\n1\tDeposit\n2\tWithdraw\n3\tTransfer\n4\tExit");
            try{
                int choice = in.nextInt();
                if(choice > 4 || choice < 0){
                    throw new Exception();
                }
                if (choice < 4){
                    customerState(customer, choice);
                }
                else{
                    done = true;
                }
            }
            catch(Exception e){
                System.out.println("That is not a valid input");
            }
        }        
    }

    private static void createAccountState(BankTeller teller){
        System.out.println("What type of account would you like to make? (0 for checking, 1 for savings)");
        try{
            int account = in.nextInt();
            if (account < 0 || account > 1){
                throw new Exception();
            }
            if (account == 0){
                System.out.println("Enter starting balance:");
                try{
                    double startBal = in.nextDouble();
                    System.out.println("Enter Customer id");
                    try{
                        int id = in.nextInt();
                        try{
                            Customer customer = bank.getCustomers().get(id);
                            AbstractAccount newAccount = teller.createAccount(customer, account, 0, 0, startBal);
                            bank.addAccount(newAccount);
                        }
                        catch(Exception e){
                            System.out.println("This customer does not exist, a new one will be made with the id: " + nextId);
                            System.out.println("Enter a new password:");
                            try{
                                String password = in.next();
                                Customer customer = teller.createAccount(nextId, password, account, 0, 0, startBal);
                                nextId++;
                                bank.addCustomer(customer);
                            }
                            catch(Exception e1){
                                System.out.println("Invalid password");
                            }
                        }
                    }
                    catch(Exception e){
                        System.out.println("Invalid id");
                    }
                }
                catch(Exception e){
                    System.out.println("Invalid amount");
                }
            }
            else{
                System.out.println("Enter starting balance:");
                try{
                    double startBal = in.nextDouble();
                    System.out.println("Enter withdraw limit");
                    try{
                        double withdrawLimit = in.nextDouble();
                        System.out.println("Enter interest rate (out of 100)");
                        try{
                            double interestRate = in.nextDouble();

                            System.out.println("Enter Customer id");
                            try{
                                int id = in.nextInt();
                                try{
                                    Customer customer = bank.getCustomers().get(id);
                                    if (customer == null){
                                        throw new Exception();
                                    }
                                    bank.createNewAccount(teller, customer, account, withdrawLimit, interestRate, startBal);
                                }
                                catch(Exception e){
                                    System.out.println("This customer does not exist, a new one will be made with the id: " + nextId);
                                    System.out.println("Enter a new password:");
                                    try{
                                        String password = in.next();
                                        bank.createNewAccount(teller, nextId, password, account, withdrawLimit, interestRate, startBal);
                                        nextId++;
                                    }
                                    catch(Exception e1){
                                        System.out.println("Invalid password");
                                    }
                                }
                            }
                            catch(Exception e){
                                System.out.println("Invalid id");
                            }
                        }
                        catch(Exception e){
                            System.out.println("Invalid interest rate");
                        }
                    }
                    catch(Exception e){
                        System.out.println("Invalid withdraw limit");
                    }
                }
                catch(Exception e){
                    System.out.println("Invalid amount");
                }
            }
        }
        catch(Exception e){
            System.out.println("Not a valid account type.");
        }
    }

    private static void tellerState(BankTeller teller){
        boolean done = false;
        while(!done){
            System.out.println("Options:\n0\tCheck Balance\n1\tDeposit\n2\tWithdraw\n3\tTransfer\n4\tCreate Account\n5\tExit");
            try{
                int choice = in.nextInt();
                if(choice > 5 || choice < 0){
                    throw new Exception();
                }
                if (choice < 4){
                    System.out.println("Enter customer's id");
                    try{
                        int customerId = in.nextInt();
                        Customer customer = bank.getCustomers().get(customerId);
                        customerState(customer, choice);
                    }
                    catch(Exception e){
                        System.out.println("Invalid ID");
                    }
                }
                else if (choice == 4){
                    createAccountState(teller);
                }
                else{
                    done = true;
                }
            }
            catch(Exception e){
                System.out.println("That is not a valid input");
            }
        }
    }

    private static void adminState(BankAdminSoftware admin){
        boolean done = false;
        while(!done){
            System.out.println("Options:\n0\tGet Total Balance\n1\tReport of Suspicious Accounts\n2\tExit");
            try{
                int choice = in.nextInt();
                if (choice < 0 || choice > 2){
                    throw new Exception();
                }if (choice == 0){
                    System.out.println(admin.checkMoneyTotal());
                }else if (choice == 1){
                    System.out.println(admin.reportOfSuspisciousAccounts());
                }else{
                    done = true;
                }
            }
            catch(Exception e){
                System.out.println("That is not a valid input");
            }
        }
    }

    public static void main(String[] args) {        

        //initialize with one teller, one admin, and one customer - can make more customers if needed if making accounts
        BankAdminSoftware initialAdmin = new BankAdminSoftware(0, "password");
        bank.addAdmin(initialAdmin);
        BankTeller initialTeller = new BankTeller(0, "password");
        bank.addTeller(initialTeller);
        Customer initialCustomer = new Customer(nextId, "password");
        nextId++;
        bank.addCustomer(initialCustomer);
        bank.createNewAccount(initialTeller, initialCustomer, 0, 0, 0, 0);

        //go to state to login
        boolean done = false;
        while(!done){
            done = loginState();
        }
        //close scanner
        in.close();
    }
}