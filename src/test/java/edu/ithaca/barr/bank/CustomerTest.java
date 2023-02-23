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
        AbstractAccount testAccount = new SavingsAccount(500, 500, 0);
        assertEquals(testCustomer.savingsAccount, )
    }

    @Test
    void setCheckingsAccountTest(){
        //Not sure how to test this yet
    }

    @Test
    void getBalanceTest(){
        //
    }

    
}
