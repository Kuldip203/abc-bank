package com.abc.account;

import java.util.Map;
import java.util.Set;

import com.abc.ImmutableDate;
import com.abc.Transaction;

public interface Account {
	
	void deposit(double amount);

	void withdraw(double amount);

	double interestEarned();

	double sumTransactions();

	AccountType getAccountType();
	
	Map<ImmutableDate, Set<Transaction>> getTransactions();
	

}
