package com.abc.account;

public class SavingsAccount extends AccountBase {

	private static final double RATE_MORE_THOUSAND = 0.02/365;
	private static final double  RATE_LESS_THOUSAND = 0.01/365;

	public SavingsAccount(AccountType accountType) {
		super(accountType);
	}

	public double interestEarned() {
		double amount = sumTransactions();
		if (amount <= 1000) {
			return getInterestForThousand(amount);
		} else
			return getInterestForThousand(1000) + (amount - 1000) * RATE_MORE_THOUSAND;
	}

	private double getInterestForThousand(double amount) {
		return amount * RATE_LESS_THOUSAND;
	}
}
