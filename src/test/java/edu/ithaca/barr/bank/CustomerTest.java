package edu.ithaca.barr.bank;

import org.junit.jupiter.api.Test;

import edu.ithaca.barr.bank.account.AbstractAccount;
import edu.ithaca.barr.bank.account.BankAccount;
import edu.ithaca.barr.bank.account.CheckingAccount;
import edu.ithaca.barr.bank.account.InsufficientFundsException;
import edu.ithaca.barr.bank.account.SavingsAccount;
import edu.ithaca.barr.bank.atm.ATM;
import edu.ithaca.barr.bank.customer.Customer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class CustomerTest {
    @Test
    void setSavingsAccountTest(){
        Customer testCustomer = new Customer(111, "111");
        SavingsAccount testAccount = new SavingsAccount(500, 500, 0);
        //Equivalence Partition - savings account is null
        assertEquals(testCustomer.getSavingsAccount(), null);
        testCustomer.setSavingsAccount(testAccount);
        //Equivalence Partition - savings account is a savings accout
        assertEquals(testCustomer.getSavingsAccount(), testAccount);
        
    }

    @Test
    void setCheckingsAccountTest(){
        Customer testCustomer = new Customer(111, "111");
        CheckingAccount testAccount = new CheckingAccount(550);
        //Equivalence Partition - checking account is null
        assertEquals(testCustomer.getCheckingAccount(), null);
        testCustomer.setCheckingAccount(testAccount);
        //Equivalence Partition - checking account is a checking account
        assertEquals(testCustomer.getCheckingAccount(), testAccount);


    }

    @Test
    void getBalanceTest(){
        Customer testCustomer = new Customer(111, "111");
        CheckingAccount testAccount = new CheckingAccount(550);
        SavingsAccount testAccount2 = new SavingsAccount(500, 500, 0);
        //Equivalence Partition - balance is zero (may have to change to assertThrow IllegalArgumentException)
        assertEquals(testCustomer.getBalance(), 0);
        testCustomer.setCheckingAccount(testAccount);
        testCustomer.setSavingsAccount(testAccount2);
        //Equivalence Partition - balance is not zero
        assertEquals(testCustomer.getBalance(), 1050);
    }

}
