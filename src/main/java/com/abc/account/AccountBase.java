package com.abc.account;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.abc.DateProvider;
import com.abc.ImmutableDate;
import com.abc.Transaction;

public abstract class AccountBase implements Account {
	protected AccountType accountType;
	protected Map<ImmutableDate,Set<Transaction>> transactions;

	public AccountBase(AccountType accountType) {
		this.accountType = accountType;
		this.transactions = new TreeMap<ImmutableDate,Set<Transaction>>();
	}
	
	@Override
	public Map<ImmutableDate, Set<Transaction>> getTransactions() {
		return Collections.unmodifiableMap(transactions);
	}

	public void deposit(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException(
					"amount must be greater than zero");
		} else {
			addTransaction(amount);
		}
	}

	public void withdraw(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException(
					"amount must be greater than zero");
		} else {
			addTransaction(-amount);
		}
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public double sumTransactions() {
		double amount = 0.0;
		for (ImmutableDate date : transactions.keySet()) {
			for(Transaction  t : transactions.get(date)){
				amount += t.getAmount();
			}
		}
		return amount;
	}
	
	private void addTransaction(double amount) {
		ImmutableDate now = DateProvider.getInstance().now();
		Set<Transaction> set = transactions.get(now);
		if(set == null){
			set = new LinkedHashSet<Transaction>();
		}
		set.add(new Transaction(amount));
		transactions.put(now, set);
	}

}
