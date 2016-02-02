package com.abc;


import org.junit.Assert;
import org.junit.Test;

import com.abc.account.Account;
import com.abc.account.AccountType;
import com.abc.account.CheckingAccount;
import com.abc.account.MaxiSavingsAccount;
import com.abc.account.SavingsAccount;

public class AccountTypeTest {
	
	@Test
	public void testCreateAccount(){
		Account savingsAccount = AccountType.SAVINGS.createAccount();
		Assert.assertTrue(savingsAccount instanceof SavingsAccount);
		
		Account checkingAccount = AccountType.CHECKING.createAccount();
		Assert.assertTrue(checkingAccount instanceof CheckingAccount);
		
		Account maxiSavingAccount = AccountType.MAXI_SAVINGS.createAccount();
		Assert.assertTrue(maxiSavingAccount instanceof MaxiSavingsAccount);
		
	}
}
