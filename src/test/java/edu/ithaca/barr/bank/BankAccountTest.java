package edu.ithaca.barr.bank;
import org.junit.jupiter.api.Test;

import edu.ithaca.barr.bank.account.BankAccount;
import edu.ithaca.barr.bank.account.CheckingAccount;
import edu.ithaca.barr.bank.account.InsufficientFundsException;
import edu.ithaca.barr.bank.account.SavingsAccount;
import edu.ithaca.barr.bank.atm.ATM;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;


class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", null, 200);

        assertEquals(200, bankAccount.getBalance(), 0.001);

        //Equivalence class: have money in bank account account
        assertEquals(200, bankAccount.getBalance());
        //Equivalence class: have no money in bank account
        BankAccount bankAccount1 = new BankAccount("apple@pie.com", null, 0);
        assertEquals(0, bankAccount1.getBalance());

    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", null, 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance(), 0.001);
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));
    
        //Equivalence class: amount larger than balance
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(150));
        
        //Equivalence class: amount is smaller than balance
        bankAccount.withdraw(50);
        assertEquals(50, bankAccount.getBalance());

        //Equivalence class: amount is negative
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(-50));

        //Boundary case: amount is zero
        bankAccount.withdraw(0);
        assertEquals(50, bankAccount.getBalance());

        //Boundary case: amount is equal to balance
        bankAccount.withdraw(bankAccount.getBalance());
        assertEquals(0, bankAccount.getBalance());

        //Equivalence case: amount has decimal with more then 2 places
        BankAccount bankAccount2 = new BankAccount("test@mail.com", null, 50);
        assertThrows(IllegalArgumentException.class, ()->bankAccount2.withdraw(0.001));

    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com"));   // valid email address
        assertFalse( BankAccount.isEmailValid(""));         // empty string

        //Tests added for Task 01-02
        //Prefix tests
        assertTrue(BankAccount.isEmailValid( "abc-d@mail.com")); //Equivalence class: Valid email -  dash within prefix is valid
        assertTrue(BankAccount.isEmailValid("abc.def@mail.com")); //Equivalence class: Valid email - period within prefix is valid
        assertTrue(BankAccount.isEmailValid("abcdefg@mail.com")); //Equivalence class: Valid email - normal email with period and just letters is valid
        assertTrue(BankAccount.isEmailValid("abc_sample@mail.com")); //Equivalence class: Valid email - underscore in the middle of the prefix is valid

        assertFalse(BankAccount.isEmailValid("abcd-@mail.com")); //Equivalence class: Invalid email - prefix cannot end in a dash
        assertFalse(BankAccount.isEmailValid("abc..sample@mail.com")); //Equivalence class: Invalid email - consecutive periods not allowed in prefix
        assertFalse(BankAccount.isEmailValid(".sample@mail.com")); //Equivalence class: Invalid email - prefix must begin with letter
        assertFalse(BankAccount.isEmailValid("abc#sample@mail.com")); //Equivalence class: Invalid email - Hash mark not allowed in prefix

        //Added after code review
        assertFalse(BankAccount.isEmailValid("test @gmail.com")); //Equivalence class: Invalid email - contains whitespace in prefix


        //Domain tests
        assertTrue(BankAccount.isEmailValid("abc.def@mail.cc")); //Equivalence class: Valid email - domain with two characters at the end is valid
        assertTrue(BankAccount.isEmailValid("abc.def@mail-archive.com")); //Equivalence class: Valid email - domain with - is valid as long as it is not the last character
        assertTrue(BankAccount.isEmailValid("abc.def@mail.org")); //Equivalence class: Valid email - Domain must have at least two characters
        assertTrue(BankAccount.isEmailValid("abc.def@mail.com"));  //Equivalence class: Valid email - Domain must have at least two characters

        assertFalse(BankAccount.isEmailValid("abc.sample@mail.c")); //Equivalence class: Invalid email - last section of domain must have at least two characters
        assertFalse(BankAccount.isEmailValid("abc.sample@mail.mail#archive.com")); //Equivalence class: Invalid email - Domain must not have special character #
        assertFalse(BankAccount.isEmailValid("abc.sample@mail")); //Equivalence class: Invalid email - last section of domain must have at least two characters
        assertFalse(BankAccount.isEmailValid("abc.sample@mail..com")); //Equivalence class: Invalid email - last section of domain can not have consecutive periods

        //Added after code review
        assertFalse(BankAccount.isEmailValid("test@gma il.com")); //Equivalence class: Invalid email - contains whitespace in domain

        //Tests added for Task 01-03
        //Prefix tests
        assertFalse(BankAccount.isEmailValid("#sample@sample.com")); //Equivalence case: invalid character at beginning of prefix
        assertFalse(BankAccount.isEmailValid("sam#le@sample.com")); //Equivalence case: invalid character in middle of prefix
        assertFalse(BankAccount.isEmailValid("sample#@sample.com")); //Equivalence case:invalid character at end of prefix
        assertTrue(BankAccount.isEmailValid("sa-mple@sample.com"));  //Equivalence case: dash followed by a letter or number in prefix
        assertFalse(BankAccount.isEmailValid("sample-@sample.com")); //Equivalence case: dash, dot, or underscore not followed by a letter or number in prefix
        assertFalse(BankAccount.isEmailValid("samp-.-le@sample.com")); //Equivalence case: more than 2 dots, dashes, underscores in a row in prefix
        assertFalse(BankAccount.isEmailValid("s__ample@sample.com"));  //Boundary case: 2 dots, dashes, or underscores in a row in prefix

        //Domain tests
        assertFalse(BankAccount.isEmailValid("sample@sample.c")); //Equivalence case: less than two characters in last portion of domain
        assertTrue(BankAccount.isEmailValid("sample@sample.co")); //Boundary case: 2 characters in last portion of domain
        assertTrue(BankAccount.isEmailValid("sample@sample.com")); //Equivalence case: more than two characters in last portion of domain
        assertFalse(BankAccount.isEmailValid("sample@#sample.com")); //Equivalence case: invalid character at beginning of domain
        assertFalse(BankAccount.isEmailValid("sample@te#st.com")); //Equivalence case: invalid character in middle of domain
        assertFalse(BankAccount.isEmailValid("sample@sample.com#")); //Equivalence case: invalid character at end of domain
        assertFalse(BankAccount.isEmailValid("sample@sample.com-")); //Equivalence case: dash not followed by a letter or number in domain
        assertFalse(BankAccount.isEmailValid("sample@sa---mple.com")); //Equivalence case: more than 2 dashes in a row in domain
        assertFalse(BankAccount.isEmailValid("sample@sample.c--om"));  //Boundary case: 2 dashes in a row in domain

    }

    @Test
    void isAmountValidTest(){
        //Equivalence Case: Amount is positive
        assertTrue(BankAccount.isAmountValid(10));
        //Equivalence Case: Amount is negative
        assertFalse(BankAccount.isAmountValid(-10));
        //Equivalence Case: Amount is positive and has two decimal places
        assertTrue(BankAccount.isAmountValid(10.01));
        //Equivalence Case: Amount is positive and has more then two decimal places
        assertFalse(BankAccount.isAmountValid(10.001));
    }

    @Test
    void depositTest(){
        BankAccount bankAccount = new BankAccount("test@mail.com", null, 100);

        //Equivalence class: amount is positive
        bankAccount.deposit(100);
        assertEquals(200, bankAccount.getBalance());
        //Equivalence class: amount is zero - border case
        bankAccount.deposit(0);
        assertEquals(200, bankAccount.getBalance());
        //Equivalence class: amount is negative
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(-5));
        //Equivalence class: amount has more than 2 decimals
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(-5.005));
        //Equivalence class: amount has less than 2 decimals
        bankAccount.deposit(10.50);
        assertEquals(210.50, bankAccount.getBalance());
    }

    @Test
    void transferTest() throws InsufficientFundsException{
        //make two bank accounts, so we can test if the money in one can be transferred to another
        BankAccount bankAccount = new BankAccount("test@mail.com", null, 100);
        BankAccount transferAccount = new BankAccount("a@b.com", null, 0);

        //Equivalence class: transfer less money then account balance into the other account
        bankAccount.transfer(transferAccount,50);
        assertEquals(50, bankAccount.getBalance());
        assertEquals(50, transferAccount.getBalance());
        //Equivalence class: transfer more money then account balance into the other account
        assertThrows(InsufficientFundsException.class, () -> bankAccount.transfer(transferAccount, 500));
        //Equivalence class: transfer negative amount of money
        assertThrows(IllegalArgumentException.class, () -> bankAccount.transfer(transferAccount, -50));
        //Equivalence class: transfer an invalid amount of money, more then 2 decimal places
        assertThrows(IllegalArgumentException.class, () -> bankAccount.transfer(transferAccount, 50.001));
        //Equivalence class: transfer bank account does not exist
        assertThrows(IllegalArgumentException.class, () -> bankAccount.transfer(null, 20));
        //Equivalence class: transfer the entire bank account to the other bank account
        bankAccount.transfer(transferAccount, 50);
        assertEquals(0, bankAccount.getBalance());
        assertEquals(100, transferAccount.getBalance());
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", null, 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance(), 0.001);
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", null, 100));

        //Equivalence class: negative amount in bank account
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("test@mail.com", null, -10));
        //Equivalence class: amount has more then 2 decimal places
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("test@mail.com", null, 10.001));
    }

    //Bank System Tests --written but need to implement
    @Test
    void checkMoneyTotalTest(){
        //create bunch of accounts with 0$ in each
        //call check money total after each to ensure there is 0$ in bank
        //deposit varying amounts of money in each account
        //call check money total after each to ensure everything is updated
        //create a bunch of accounts with varying amount of money in them
        //call check money total after each to ensure there is the right amount of $
        //withdraw a varying amount of money from each account
        //call check money total after each to ensure it is updated correctly
        //transfer money from some bank accounts to other bank accounts and ensure bank total stays the same
        //freeze and unfreeze some accounts and ensure money stays the same
        //close some accounts and check if the money was removed from the bank
        int i = 100;
        int x = 20;
        assertEquals(20*100, i*x);
    }

    @Test
    void accountsTest(){
        //create lots of accounts, each iteration checking that it was added
        //freeze and unfreeze some accounts, ensure that # of accounts does not change
        //report some suspicious accounts, ensure that # of accounts does not change
        //close out each account, making sure each was actually closed
    }

    @Test
    void reportSuspiciousAccountsTest(){
        //create a bunch of accounts and add it to report suspicious accounts, ensuring that the method is returning everything
        //correctly in a list
        //ensure if you pass invalid id in it throws an error
        //remove a bunch of accounts from the list and check if they are there anymore
    }

    @Test
    void freezeUnfreezeAccountTest(){
        //create a bunch of accounts, freezing and unfreezing as you go and ensuring that they stayed frozen/unfrozen
        //ensure if accounts are frozen, they cannot withdraw, deposit, or transfer
        //report suspicious accounts and ensure they do not freeze by themselves
        //close accounts and ensure they arent still listed as frozen/unfrozen
         //ensure if you pass invalid id in it throws an error
    }

    @Test
    void transactionHistoryTest() throws InsufficientFundsException{
        CheckingAccount testAccount = new CheckingAccount(200);
        CheckingAccount testAccount2 = new CheckingAccount(0);
        testAccount.deposit(100);
        assertEquals("Deposited 100.0\n", testAccount.historyToString());
        testAccount.deposit(100);
        assertEquals("Deposited 100.0\nDeposited 100.0\n", testAccount.historyToString());
        testAccount.withdraw(100);
        assertEquals("Deposited 100.0\nDeposited 100.0\nWithdrew 100.0\n", testAccount.historyToString());
        testAccount.transfer(100, testAccount2);
        assertEquals("Deposited 100.0\nDeposited 100.0\nWithdrew 100.0\nWithdrew 100.0\n", testAccount.historyToString());
        assertEquals("Deposited 100.0\n", testAccount2.historyToString());

        SavingsAccount testAccount3 = new SavingsAccount(200,1000,10);
        SavingsAccount testAccount4 = new SavingsAccount(0,1000,10);
        testAccount3.deposit(100);
        assertEquals("Deposited 100.0\n", testAccount3.historyToString());
        testAccount3.deposit(100);
        assertEquals("Deposited 100.0\nDeposited 100.0\n", testAccount3.historyToString());
        testAccount3.withdraw(100);
        assertEquals("Deposited 100.0\nDeposited 100.0\nWithdrew 100.0\n", testAccount3.historyToString());
        testAccount3.transfer(100, testAccount4);
        assertEquals("Deposited 100.0\nDeposited 100.0\nWithdrew 100.0\nWithdrew 100.0\n", testAccount3.historyToString());
        assertEquals("Deposited 100.0\n", testAccount4.historyToString());
    }

    @Test
    void isNumberValidTest() {
        // checks to see if a double has two decimals or less
        assertTrue(CheckingAccount.isNumberValid(100));
        assertTrue(CheckingAccount.isNumberValid(100.1));
        assertTrue(CheckingAccount.isNumberValid(100.11));
        assertFalse(CheckingAccount.isNumberValid(100.111));

        // amount is a positive number
        assertTrue(CheckingAccount.isNumberValid(10));
        assertFalse(CheckingAccount.isNumberValid(-10));
        assertFalse(CheckingAccount.isNumberValid(-100));
    }

    @Test
    void withdrawLimitTest() throws InsufficientFundsException {
        SavingsAccount bankAccount = new SavingsAccount(500, 300, 10.0);
        bankAccount.withdraw(300);
        assertEquals(200, bankAccount.getBalance(), 0.001);

        // Withdraw limit exceeded
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(300));

        // Negative number withdrawn
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(-100));

        // Too many decimal places
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(100.999));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(100.001));

        // Balance does not change when an excepetion is thrown
        assertEquals(200, bankAccount.getBalance());
        // Remaining Withdraw Limit Can be 0.
        assertEquals(0,bankAccount.getRemainingWithdraw());
        //Reset Withdraw Limit
        bankAccount.resetWithdrawLimit();
        assertEquals(300, bankAccount.getRemainingWithdraw());        
        assertEquals(200,bankAccount.getBalance());
        //Insuficcient Funds Exception
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));

    }

    @Test
    void interestTest(){
        SavingsAccount bankAccount = new SavingsAccount( 200, 500, 10.0);
        assertEquals(10, bankAccount.getInterest());
        assertEquals(20, bankAccount.calculateInterest());
        bankAccount.addInterest();
        assertEquals(220, bankAccount.getBalance());
    }

    private ATM atm;
    private BankAccount account;

    @BeforeEach
    //Integration Test - Matt 
    void setUp() {
        atm = new ATM();
        account = new BankAccount("johndoe@example.com", "password", 1000.0);
    }

    @Test
    void testWithdraw() throws InsufficientFundsException {
        // Withdraw some money from the account using the ATM
        atm.withdraw(account, 500.0);
        // Check that the account balance was updated correctly
        assertEquals(500.0, account.getBalance(), 0.01);
    }

    @Test
    void testDeposit() {
        // Deposit some money into the account using the ATM
        atm.deposit(account, 500.0);
        // Check that the account balance was updated correctly
        assertEquals(1500.0, account.getBalance(), 0.01);
    }

    @Test
    void testTransfer() throws InsufficientFundsException {
        // Create a second bank account to transfer money to
        BankAccount otherAccount = new BankAccount("janedoe@example.com", "password", 500.0);
        // Transfer some money from the first account to the second account using the ATM
        atm.transfer(250.0, account, otherAccount);
        // Check that the balances of both accounts were updated correctly
        assertEquals(750.0, account.getBalance(), 0.01);
        assertEquals(750.0, otherAccount.getBalance(), 0.01);
    }
}


