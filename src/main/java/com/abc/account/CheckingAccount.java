package com.abc.account;


public class CheckingAccount extends AccountBase{

	private static final double RATE = 0.01/365; //1% accured daily

	public CheckingAccount(AccountType accountType) {
		super(accountType);
	}

	@Override
	public double interestEarned() {
		double amount = sumTransactions();
		return amount * RATE;
	}
}
