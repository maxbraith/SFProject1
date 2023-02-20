package edu.ithaca.barr.bank;

import java.util.Scanner;

import edu.ithaca.barr.bank.account.AbstractAccount;
import edu.ithaca.barr.bank.atm.ATM;
import edu.ithaca.barr.bank.bankadminsystem.BankAdminSoftware;
import edu.ithaca.barr.bank.customer.Customer;
import edu.ithaca.barr.bank.teller.BankTeller;

public class UserInterface {

    //initial objects needed
    private static Scanner in = new Scanner(System.in);
    private static ATM atm = new ATM();
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
                                    Customer customer = atm.customerLogIn(id, password);
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
                                    BankTeller teller = atm.tellerLogIn(id, password);
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
                                    BankAdminSoftware admin = atm.adminLogIn(id, password);
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


    public static void main(String[] args) {        

        //initialize with one teller, one admin, and one customer - can make more customers if needed if making accounts
        ATM intialAdmin = new ATM(0, "password");
        atm.addAdmin(intialAdmin);
        BankTeller intialTeller = new BankTeller(0, "password");
        atm.addTeller(intialTeller);
        Customer intialCustomer = new Customer(nextId, "password");
        nextId++;
        atm.addCustomer(intialCustomer);
        atm.createNewAccount(intialTeller, intialCustomer, 0, 0, 0, 0);

        //go to state to login
        boolean done = false;
        while(!done){
            done = loginState();
        }
        

        //close scanner
        in.close();
    }
}

