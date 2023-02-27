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
        bank.addCustomer(testCustomer);
        BankAdminSoftware testAdmin = new BankAdminSoftware(123, "123");
        bank.addAdmin(testAdmin);
        BankTeller teller = new BankTeller(123, "123");
        bank.addTeller(teller);
        
        //Equivalence Partition - No accounts in arrayList
        assertEquals(0, testAdmin.checkMoneyTotal(bank));

        bank.createNewAccount(teller, testCustomer, 0, 25000, 1, 0);
        //Equivalence Partition - Empty balance in arrayList
        assertEquals(0, testAdmin.checkMoneyTotal(bank));

        bank.createNewAccount(teller, testCustomer, 1, 25000, 1, 100);
        //Equivalence Partition - Balance of 200 in arrayList
        assertEquals(100, testAdmin.checkMoneyTotal(bank));
        
        
    }
    
}
