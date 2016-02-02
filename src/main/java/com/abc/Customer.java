package com.abc;

import java.util.LinkedHashMap;
import java.util.Map;

import com.abc.account.Account;
import com.abc.account.AccountType;

public class Customer {
    private String name;
    private Map<AccountType, Account> mapTypeAccount;

    public Customer(String name) {
        this.name = name;
        this.mapTypeAccount = new LinkedHashMap<AccountType, Account>();
    }

    public String getName() {
        return name;
    }

    public Customer openAccount(Account account) {
        mapTypeAccount.put(account.getAccountType(), account);
        return this;
    }

    public int getNumberOfAccounts() {
        return mapTypeAccount.size();
    }

    public double totalInterestEarned() {
        double total = 0;
        for (Account a : mapTypeAccount.values())
            total += a.interestEarned();
        return total;
    }

    public String getStatement() {
    	return StatementBuilder.buildStatement(name, mapTypeAccount.values());
    }
    
    public boolean transfer(AccountType from , AccountType to, double amount){
    	Account fromAccount = mapTypeAccount.get(from);
    	Account toAccount = mapTypeAccount.get(to);
    	if(isValidTransaction(amount, fromAccount, toAccount)) {
    		fromAccount.withdraw(amount);
    		toAccount.deposit(amount);
    		return true;
    	}
    	else {
    		return false;
    	}
    }

	private boolean isValidTransaction(double amount, Account fromAccount,
			Account toAccount) {
		
		if(fromAccount == null || toAccount == null) {
    		return false;
    	}
    	else if(fromAccount.sumTransactions() < amount) {
    		return false;
    	}
		
		return true;
	}

}
