package com.abc.account;

import com.abc.ImmutableDate;
import com.abc.Transaction;
import com.abc.TransactionType;

public class MaxiSavingsAccount extends AccountBase {

	private static final double RATE_NO_RECENT_WITHDRAW = 0.05/365;
	private static final double FLAT_RATE = 0.01/365;
	
	
	public MaxiSavingsAccount(AccountType accountType) {
		
		super(accountType);
	}

	@Override
	public double interestEarned() {
		ImmutableDate lastWithdraw = getLastWithdrawTransactionDate();
		if(lastWithdraw == null || !lastWithdraw.isRecentThanNdays(10)){
			return sumTransactions()*RATE_NO_RECENT_WITHDRAW;
		}
		else {
			return sumTransactions()*FLAT_RATE;
		}
	}

	private ImmutableDate getLastWithdrawTransactionDate() {
		for(ImmutableDate date : transactions.keySet()){
			for(Transaction t : transactions.get(date)) {
				if(t.getTransactionType() == TransactionType.WITHDRAW) {
					return date;
				}
			}
		}
		return null;
	}

}
