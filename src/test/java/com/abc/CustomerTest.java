package com.abc;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.abc.account.Account;
import com.abc.account.AccountType;

public class CustomerTest {

    @Test
    public void testOneAccount(){
        Customer oscar = new Customer("Oscar").openAccount(AccountType.SAVINGS.createAccount());
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount(){
        Customer oscar = new Customer("Oscar")
                .openAccount(AccountType.SAVINGS.createAccount());
        oscar.openAccount(AccountType.CHECKING.createAccount());
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Test
    public void testThreeAcounts() {
        Customer oscar = new Customer("Oscar")
                .openAccount(AccountType.SAVINGS.createAccount());
        oscar.openAccount(AccountType.CHECKING.createAccount());
        oscar.openAccount(AccountType.MAXI_SAVINGS.createAccount());
        assertEquals(3, oscar.getNumberOfAccounts());
    }
    
    @Test
    public void testTransferBetweenAccounts(){
    	Customer kuldip = new Customer("Kuldip");
    	Account savingsAccount = AccountType.SAVINGS.createAccount();
		kuldip.openAccount(savingsAccount);
    	Account checkingAccount = AccountType.CHECKING.createAccount();
		kuldip.openAccount(checkingAccount);
		
		checkingAccount.deposit(1000);
		
		boolean success = kuldip.transfer(AccountType.CHECKING, AccountType.SAVINGS, 900);
		
		Assert.assertTrue(success);
		double delta = 0.0;
		Assert.assertEquals(100, checkingAccount.sumTransactions(), delta);
		Assert.assertEquals(900, savingsAccount.sumTransactions(), delta);
    }
    
    @Test
    public void testInvalidTransferBetweenAccounts(){
    	Customer kuldip = new Customer("Kuldip");
    	Account savingsAccount = AccountType.SAVINGS.createAccount();
		kuldip.openAccount(savingsAccount);
    	Account checkingAccount = AccountType.CHECKING.createAccount();
		kuldip.openAccount(checkingAccount);
		
		checkingAccount.deposit(1000);
		
		boolean success = kuldip.transfer(AccountType.CHECKING, AccountType.SAVINGS, 5000);
		
		Assert.assertFalse(success);
		double delta = 0.0;
		Assert.assertEquals(1000, checkingAccount.sumTransactions(), delta);
		Assert.assertEquals(0, savingsAccount.sumTransactions(), delta);
    }
    
    @Test
    public void testTotalInterstEarnedSavings(){
    	Set<Account> accounts = new HashSet<Account>();
        Account checkingAccount = AccountType.CHECKING.createAccount();
        Account savingsAccount = AccountType.SAVINGS.createAccount();
        checkingAccount.deposit(1000);
        savingsAccount.deposit(1000);
        savingsAccount.withdraw(200.0);
        accounts.add(checkingAccount);
        accounts.add(savingsAccount);
        
        Customer customer = new Customer("Kuldip");
        customer.openAccount(savingsAccount);
        double interstEarnedForThousand = customer.totalInterestEarned();
        double delta = 0.009;
		Assert.assertEquals(1000*0.01/365, interstEarnedForThousand, delta);
		
		savingsAccount.deposit(1000);
		double interstEarned = customer.totalInterestEarned();
        delta = 0.009;
		Assert.assertEquals(interstEarnedForThousand + 1000*0.02/365, interstEarned, delta);
		
    }
    
    @Test
    public void testTotalInterstEarnedCheckings(){
        Account checkingAccount = AccountType.CHECKING.createAccount();
        checkingAccount.deposit(1000);
        
        Customer customer = new Customer("Kuldip");
        customer.openAccount(checkingAccount);
        double interstEarnedForThousand = customer.totalInterestEarned();
        double delta = 0.009;
		Assert.assertEquals(1000*0.01/365, interstEarnedForThousand, delta);
    }
    
    @Test
    public void testTotalInterstEarnedMaxiSaving(){
        Account maxiSavingAccount = AccountType.MAXI_SAVINGS.createAccount();
        maxiSavingAccount.deposit(1000);
        
        Customer customer = new Customer("Kuldip");
        customer.openAccount(maxiSavingAccount);
        double interstEarnedForThousand = customer.totalInterestEarned();
        double delta = 0.009;
		Assert.assertEquals(1000*0.05/365, interstEarnedForThousand, delta);
    }
    
    @Test
    public void testTotalInterstEarnedMaxiSavingWithWithdrawal(){
        Account maxiSavingAccount = AccountType.MAXI_SAVINGS.createAccount();
        maxiSavingAccount.deposit(2000);
        maxiSavingAccount.withdraw(1000);
        
        Customer customer = new Customer("Kuldip");
        customer.openAccount(maxiSavingAccount);
        double interstEarnedForThousand = customer.totalInterestEarned();
        double delta = 0.009;
		Assert.assertEquals(1000*0.01/365, interstEarnedForThousand, delta);
    }
}
