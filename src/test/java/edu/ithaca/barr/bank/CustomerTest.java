package edu.ithaca.barr.bank;

import org.junit.jupiter.api.Test;

import edu.ithaca.barr.bank.account.CheckingAccount;
import edu.ithaca.barr.bank.account.InsufficientFundsException;
import edu.ithaca.barr.bank.account.SavingsAccount;
import edu.ithaca.barr.bank.customer.Customer;

import static org.junit.jupiter.api.Assertions.*;

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
        assertThrows(NullPointerException.class, ()-> testCustomer.getBalance());
        
        testCustomer.setCheckingAccount(testAccount);
        //Equivalence Partition - one account is null
        assertThrows(NullPointerException.class, ()-> testCustomer.getBalance());
        
        testCustomer.setSavingsAccount(testAccount2);
        //Equivalence Partition - balance is not zero
        assertEquals(testCustomer.getBalance(), 1050);

        testAccount.withdraw(550);
        testAccount2.withdraw(500);
        //Equivalence Partition - balance is zero
        assertEquals(0, testCustomer.getBalance());
        


    }

}
