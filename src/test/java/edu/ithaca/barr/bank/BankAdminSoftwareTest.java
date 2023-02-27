package edu.ithaca.barr.bank;
import org.junit.jupiter.api.Test;

import edu.ithaca.barr.bank.bankadminsystem.BankAdminSoftware;
import edu.ithaca.barr.bank.customer.Customer;
import edu.ithaca.barr.bank.account.AbstractAccount;
import edu.ithaca.barr.bank.account.Bank;
import edu.ithaca.barr.bank.account.BankAccount;
import edu.ithaca.barr.bank.account.CheckingAccount;
import edu.ithaca.barr.bank.account.InsufficientFundsException;
import edu.ithaca.barr.bank.account.SavingsAccount;
import edu.ithaca.barr.bank.atm.ATM;
import edu.ithaca.barr.bank.teller.BankTeller;

import static org.junit.jupiter.api.Assertions.*;

import javax.security.auth.login.AccountNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
public class BankAdminSoftwareTest {

//Tests For Bank System - Written by Giovanni

    @Test
    void checkMoneyTotalTest() throws InsufficientFundsException{
        BankAdminSoftware initialAdmin = new BankAdminSoftware(0, "password");
        Bank bank = new Bank();
        bank.addAdmin(initialAdmin);
    
        BankTeller initialTeller = new BankTeller(0, "password");
        bank.addTeller(initialTeller);
    
        Customer initialCustomer1 = new Customer(0, "password");
        bank.addCustomer(initialCustomer1);
    
        Customer initialCustomer2 = new Customer(0, "password");
        bank.addCustomer(initialCustomer2);
    
        Customer initialCustomer3 = new Customer(0, "password");
        bank.addCustomer(initialCustomer3);
    
        bank.createNewAccount(initialTeller, initialCustomer1, 0, 0, 0, 1);
        bank.createNewAccount(initialTeller, initialCustomer2, 0, 0, 0, 2);
        bank.createNewAccount(initialTeller, initialCustomer3, 0, 0, 0, 3);
        assertEquals(6, bank.checkMoneyTotal());
        initialTeller.deposit(bank.getAllAccounts().get(0), 1);
        initialTeller.deposit(bank.getAllAccounts().get(1), 2);
        initialTeller.deposit(bank.getAllAccounts().get(2), 3);
        assertEquals(12, bank.checkMoneyTotal());
        initialTeller.createAccount(initialCustomer1, 0, 0, 0, 4);
        initialTeller.createAccount(initialCustomer1, 0, 0, 0, 0);
        assertEquals(12, bank.checkMoneyTotal());
        bank.createNewAccount(initialTeller,initialCustomer1,1,50,0,6);
        assertEquals(18, bank.checkMoneyTotal());
        bank.createNewAccount(initialTeller,initialCustomer2,1,50,0,9);
        assertEquals(27, bank.checkMoneyTotal());
        initialTeller.withdraw(bank.getAllAccounts().get(4), 3);
        assertEquals(24, bank.checkMoneyTotal());
        initialTeller.withdraw(bank.getAllAccounts().get(3), 1);
        assertEquals(23, bank.checkMoneyTotal());
        initialTeller.withdraw(bank.getAllAccounts().get(4), 6);
        assertEquals(17, bank.checkMoneyTotal());
        initialTeller.transfer(2, bank.getAllAccounts().get(3), bank.getAllAccounts().get(0));
        initialTeller.transfer(3, bank.getAllAccounts().get(2), bank.getAllAccounts().get(0));
        assertEquals(17, bank.checkMoneyTotal());
        initialAdmin.freezeAccount(bank.getAllAccounts().get(2), bank);
        initialAdmin.freezeAccount(bank.getAllAccounts().get(4), bank);
        initialAdmin.freezeAccount(bank.getAllAccounts().get(3), bank);
        assertEquals(17, bank.checkMoneyTotal());
        initialAdmin.unfreezeAccount(bank.getAllAccounts().get(2), bank);
        initialAdmin.unfreezeAccount(bank.getAllAccounts().get(4), bank);
        initialAdmin.unfreezeAccount(bank.getAllAccounts().get(3), bank);
        assertEquals(17, bank.checkMoneyTotal());
        initialTeller.closeAccount(bank.getAllAccounts().get(4), bank);
        assertEquals(17, bank.checkMoneyTotal());
    }

    @Test
    void accountsTest(){
        Bank bank = new Bank();
        BankAdminSoftware bankad = new BankAdminSoftware(333, "fk");
        bank.addAdmin(bankad);
        BankTeller teller = new BankTeller(333, "jri");
        Customer initialCustomer1 = new Customer(0, "password");
        bank.addCustomer(initialCustomer1);
        Customer initialCustomer2 = new Customer(0, "password");
        bank.addCustomer(initialCustomer2);
        assertEquals(0,bank.accounts.size());
        bank.createNewAccount(teller, initialCustomer1, 0, 0, 0, 0);
        assertEquals(1,bank.accounts.size());
        bank.createNewAccount(teller, initialCustomer1, 1, 0, 0, 0);
        assertEquals(2,bank.accounts.size());
        bank.createNewAccount(teller, initialCustomer2, 1, 0, 0, 0);
        assertEquals(3,bank.accounts.size());
        bankad.freezeAccount(bank.accounts.get(0), bank);
        bankad.freezeAccount(bank.accounts.get(1), bank);
        bankad.freezeAccount(bank.accounts.get(2), bank);
        assertEquals(3,bank.accounts.size());
        bankad.unfreezeAccount(bank.accounts.get(0), bank);
        bankad.unfreezeAccount(bank.accounts.get(1), bank);
        bankad.unfreezeAccount(bank.accounts.get(2), bank);
        assertEquals(3,bank.accounts.size());
        teller.closeAccount(bank.accounts.get(2), bank);
        assertEquals(2,bank.accounts.size());
        teller.closeAccount(bank.accounts.get(1), bank);
        assertEquals(1,bank.accounts.size());
        teller.closeAccount(bank.accounts.get(0), bank);
        assertEquals(0,bank.accounts.size());
    }

    @Test
    void markSuspiciousAccountsTest() throws AccountNotFoundException{
        Bank bank = new Bank();
        BankAdminSoftware bankad = new BankAdminSoftware(333, "fk");
        bank.addAdmin(bankad);
        BankTeller teller = new BankTeller(333, "jri");
        Customer initialCustomer1 = new Customer(0, "password");
        bank.addCustomer(initialCustomer1);
        Customer initialCustomer2 = new Customer(0, "password");
        bank.addCustomer(initialCustomer2);
        bank.createNewAccount(teller, initialCustomer1, 0, 0, 0, 0);
        bank.createNewAccount(teller, initialCustomer1, 1, 0, 0, 0);
        bank.createNewAccount(teller, initialCustomer2, 1, 0, 0, 0);
        assertEquals(0,bank.flaggedAccounts.size());
        bankad.markAsSuspiscious(initialCustomer1.getCheckingAccount(), bank);
        assertEquals(1,bank.flaggedAccounts.size());
        bankad.markAsSuspiscious(initialCustomer1.getSavingsAccount(), bank);
        assertEquals(2,bank.flaggedAccounts.size());
        bankad.markAsSuspiscious(initialCustomer2.getSavingsAccount(), bank);
        assertEquals(3,bank.flaggedAccounts.size());
        Assertions.assertThrows(AccountNotFoundException.class, ()-> bankad.markAsSuspiscious(initialCustomer2.getCheckingAccount(), bank));
        bankad.unMarkAsSuspicious(initialCustomer1.getCheckingAccount(), bank);
        assertEquals(2,bank.flaggedAccounts.size());
        bankad.unMarkAsSuspicious(initialCustomer2.getSavingsAccount(), bank);
        assertEquals(1,bank.flaggedAccounts.size());
        bankad.unMarkAsSuspicious(initialCustomer1.getSavingsAccount(), bank);
        assertEquals(0,bank.flaggedAccounts.size());
    }

    @Test
    void freezeUnfreezeAccountTest(){
        Bank bank = new Bank();
        BankTeller teller = new BankTeller(333, "pp");
        bank.addTeller(teller);
        BankAdminSoftware bankad = new BankAdminSoftware(333, "fk");
        bank.addAdmin(bankad);
        Customer initialCustomer1 = new Customer(0, "password");
        bank.addCustomer(initialCustomer1);
        Customer initialCustomer2 = new Customer(0, "password");
        bank.addCustomer(initialCustomer2);
        Customer initialCustomer3 = new Customer(0, "password");
        bank.addCustomer(initialCustomer3);
        bank.createNewAccount(teller, initialCustomer1, 0, 0, 0, 0);
        bank.createNewAccount(teller, initialCustomer2, 0, 0, 0, 0);
        bank.createNewAccount(teller, initialCustomer3, 0, 0, 0, 0);
        assertEquals(false, bank.accounts.get(0).getStatus());
        assertEquals(false, bank.accounts.get(1).getStatus());
        assertEquals(false, bank.accounts.get(2).getStatus());
        assertEquals(0, bank.frozenAccounts.size());
        bankad.freezeAccount(bank.accounts.get(0),bank);
        assertEquals(1, bank.frozenAccounts.size());
        bankad.freezeAccount(bank.accounts.get(1),bank);
        assertEquals(2, bank.frozenAccounts.size());
        bankad.freezeAccount(bank.accounts.get(2),bank);
        assertEquals(true, bank.accounts.get(0).getStatus());
        assertEquals(true, bank.accounts.get(1).getStatus());
        assertEquals(true, bank.accounts.get(2).getStatus());
        Assertions.assertThrows(InsufficientFundsException.class, ()-> bank.accounts.get(0).withdraw(1));
        Assertions.assertThrows(IllegalArgumentException.class, ()-> bank.accounts.get(1).deposit(1));
        Assertions.assertThrows(InsufficientFundsException.class, ()-> teller.transfer(1,bank.accounts.get(2),bank.accounts.get(1)));
        assertEquals(3, bank.frozenAccounts.size());
        Assertions.assertThrows(IndexOutOfBoundsException.class, ()-> bankad.freezeAccount(bank.accounts.get(5),bank));
        bankad.unfreezeAccount(bank.accounts.get(0),bank);
        assertEquals(2, bank.frozenAccounts.size());
        bankad.unfreezeAccount(bank.accounts.get(1),bank);
        assertEquals(1, bank.frozenAccounts.size());
        bankad.unfreezeAccount(bank.accounts.get(2),bank);
        assertEquals(0, bank.frozenAccounts.size());
        assertEquals(false, bank.accounts.get(0).getStatus());
        assertEquals(false, bank.accounts.get(1).getStatus());
        assertEquals(false, bank.accounts.get(2).getStatus());
        bankad.freezeAccount(bank.accounts.get(0),bank);
        bankad.freezeAccount(bank.accounts.get(1),bank);
        teller.closeAccount(bank.accounts.get(2),bank);
        teller.closeAccount(bank.accounts.get(1),bank);
        teller.closeAccount(bank.accounts.get(0),bank);
        assertEquals(0, bank.frozenAccounts.size());
    }

}


