package com.abc;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.abc.account.Account;
import com.abc.account.AccountType;

public class StatementBuilderTest {

	private static final String FOR_ACCOUNT_CHECKING_ACCOUNT = "For Account\tChecking Account";
	private static final String TOTAL = "Total\t";
	private static final String FOR_ACCOUNT_SAVINGS_ACCOUNT = "For Account\tSavings Account";
	private static final String DEPOSIT = "\tdeposit\t";
	private static final String WITHDRAWAL = "\twithdrawal\t";
	private static final String TOTAL_IN_ALL_ACCOUNTS = "Total In All Accounts\t";

	@Test
	public void testBuildStatement(){
		
		Set<Account> accounts = new HashSet<Account>();
        Account checkingAccount = AccountType.CHECKING.createAccount();
        Account savingsAccount = AccountType.SAVINGS.createAccount();
        checkingAccount.deposit(100.0);
        savingsAccount.deposit(4000.0);
        savingsAccount.withdraw(200.0);
        accounts.add(checkingAccount);
        accounts.add(savingsAccount);
        
        String customerName = "Kuldip";
        
        String actualStatement = StatementBuilder.buildStatement(customerName, accounts);
        String[] breakup = actualStatement.split("\n");
        
        assertHeading(breakup[0], "Kuldip");
        assertHeadingForCheckingAccount(breakup[1]);
        assertDeposit(breakup[2], "$100.00");
        assertTotal(breakup[3], "$100.00");
        assertHeadingForSavingsAccount(breakup[4]);
        assertDeposit(breakup[5], "$4,000.00");
        assertWithdrawal(breakup[6], "$200.00");
        assertTotal(breakup[7], "$3,800.00");
        assertNewLine(breakup[8]);
        assertTotalInAllAcc(breakup[9], "$3,900.00");
	}

	private void assertTotalInAllAcc(String actual, String amount) {
		assertEquals(TOTAL_IN_ALL_ACCOUNTS+amount, actual);
	}

	private void assertWithdrawal(String actual, String amount) {
		assertEquals(actual, WITHDRAWAL+amount);
	}

	private void assertHeadingForSavingsAccount(String actual) {
		assertEquals(FOR_ACCOUNT_SAVINGS_ACCOUNT, actual);
	}

	private void assertNewLine(String actual) {
		assertEquals("", actual);
	}

	private void assertTotal(String actual, String amount) {
		assertEquals(TOTAL+amount, actual);
	}

	private void assertDeposit(String actual, String amount) {
		assertEquals(DEPOSIT+amount, actual);
	}

	private void assertHeadingForCheckingAccount(String actual) {
		assertEquals(FOR_ACCOUNT_CHECKING_ACCOUNT, actual);
	}

	private void assertHeading(String actual, String name) {
		assertEquals(StatementBuilder.STATEMENT_FOR+name, actual);
	}
}
