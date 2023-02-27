package edu.ithaca.barr.bank;

import org.junit.jupiter.api.Test;

import edu.ithaca.barr.bank.account.Bank;
import edu.ithaca.barr.bank.bankadminsystem.BankAdminSoftware;
import edu.ithaca.barr.bank.customer.Customer;

import static org.junit.jupiter.api.Assertions.*;

public class BankAdminTest {
    
    @Test
    void getMoneyTotalTest(){
        Bank bank = new Bank();
        Customer testCustomer = new Customer(333, "12");
        BankAdminSoftware testAdmin = new BankAdminSoftware(123, "123");
        assertEquals(0, testAdmin.checkMoneyTotal(bank));
        

        
        
    }
    
}
