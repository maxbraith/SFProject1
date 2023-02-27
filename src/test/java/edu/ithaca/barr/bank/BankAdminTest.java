package edu.ithaca.barr.bank;

import org.junit.jupiter.api.Test;

import edu.ithaca.barr.bank.account.Bank;
import edu.ithaca.barr.bank.bankadminsystem.BankAdminSoftware;
import edu.ithaca.barr.bank.customer.Customer;
import edu.ithaca.barr.bank.teller.BankTeller;

import static org.junit.jupiter.api.Assertions.*;

public class BankAdminTest {
    
    @Test
    void getMoneyTotalTest(){
        Bank bank = new Bank();
        Customer testCustomer = new Customer(333, "12");
        BankAdminSoftware testAdmin = new BankAdminSoftware(123, "123");
        BankTeller teller = new BankTeller(123, "123");
        
        //Equivalence Partition - No accounts in arrayList
        assertEquals(0, testAdmin.checkMoneyTotal(bank));

        //

        


        
        
    }
    
}
