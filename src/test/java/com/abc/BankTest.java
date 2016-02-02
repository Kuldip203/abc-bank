package com.abc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.abc.account.Account;
import com.abc.account.AccountType;

public class BankTest {
    private static final double DOUBLE_DELTA = 0.009; // correct to 2 decimal places

    @Test
    public void customerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(AccountType.CHECKING.createAccount());
        bank.addCustomer(john);
        
        // TODO create Customer Summary class.
        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void checkingAccount() {
        Bank bank = new Bank();
        Account checkingAccount = AccountType.CHECKING.createAccount();
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(1000.0);

        assertEquals(1000*0.01/365, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void savings_account() {
        Bank bank = new Bank();
        Account checkingAccount = AccountType.SAVINGS.createAccount();
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(1500.0);

        assertEquals(1000*0.01/365 + 500*.02/365, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void maxi_savings_account() {
        Bank bank = new Bank();
        Account maxiSaving = AccountType.MAXI_SAVINGS.createAccount();
        bank.addCustomer(new Customer("Bill").openAccount(maxiSaving));

        maxiSaving.deposit(3000.0);

        assertEquals(3000*0.05/365, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

}
