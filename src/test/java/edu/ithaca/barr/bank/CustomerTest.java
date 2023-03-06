package edu.ithaca.barr.bank;

import org.junit.jupiter.api.Test;

import edu.ithaca.barr.bank.account.CheckingAccount;
import edu.ithaca.barr.bank.account.InsufficientFundsException;
import edu.ithaca.barr.bank.account.SavingsAccount;
import edu.ithaca.barr.bank.customer.Customer;

import static org.junit.jupiter.api.Assertions.*;

//All tests written by Max
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
    void getBalanceTest() throws InsufficientFundsException{
        Customer testCustomer = new Customer(111, "111");
        CheckingAccount testAccount = new CheckingAccount(550);
        SavingsAccount testAccount2 = new SavingsAccount(500, 500, 0);
        //Equivalence Partition - accounts are null
        assertEquals(0, testCustomer.getBalance());
        
        testCustomer.setCheckingAccount(testAccount);
        //Equivalence Partition - one account is null
        assertEquals(550, testCustomer.getBalance());
        
        testCustomer.setSavingsAccount(testAccount2);
        //Equivalence Partition - balance is not zero
        assertEquals(testCustomer.getBalance(), 1050);

        testAccount.withdraw(550);
        testAccount2.withdraw(500);
        //Equivalence Partition - balance is zero
        assertEquals(0, testCustomer.getBalance());
        


    }


    //Integration tests - Max
    @Test
    void integrationCustomerTests() throws InsufficientFundsException{
        Customer testCustomer = new Customer(222, "password");
        SavingsAccount testSavings = new SavingsAccount(0, 500, 0);
        CheckingAccount testCheckings = new CheckingAccount(0);
        testCustomer.setCheckingAccount(testCheckings);
        testCustomer.setSavingsAccount(testSavings);
        //Uses teller for deposit and withdraw method
        testCustomer.depositCheckingAccount(1000);
        testCustomer.depositSavingsAccount(500);
        //getBalance uses methods from specific accounts
        assertEquals(1500, testCustomer.getBalance());
        testCustomer.withdrawSavingsAccount(250);
        assertEquals(1250, testCustomer.getBalance());
    }

}
