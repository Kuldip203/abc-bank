package com.abc;

import static java.lang.Math.abs;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.abc.account.Account;

public class StatementBuilder {

	private static final String TOTAL = "Total\t";
	private static final String TOTAL_ALL = "\nTotal In All Accounts\t";

	public static final String STATEMENT_FOR = "Statement for ";

	private static final String FOR_ACCOUNT = "For Account\t";
	
	public static String buildStatement(String customerName, Collection<Account> collection){
		StringBuilder sb = new StringBuilder();
		sb.append(STATEMENT_FOR);
		sb.append(customerName + "\n");
		
		double total=0.0;
		for(Account a : collection) {
			sb.append(FOR_ACCOUNT);
			sb.append(a.getAccountType().getStringValue() + "\n");
			buildForAccount(sb, a); 
			sb.append(getTotalEntry(a.sumTransactions(), TOTAL));
			
			total += a.sumTransactions();
		}
		
		
		sb.append(getTotalEntry(total, TOTAL_ALL));
		
		return sb.toString();
	}

	private static void buildForAccount(StringBuilder sb, Account a) {
		Map<ImmutableDate, Set<Transaction>> transactions = a.getTransactions();
		for(ImmutableDate date : transactions.keySet()){
			for(Transaction t : transactions.get(date)){
				sb.append(createStatementEntry(date, t));
			}
		}
	}

	public static String getTotalEntry(double total, String totalAll) {
		return totalAll + toDollars(total) + "\n";
	}

	public static String createStatementEntry(ImmutableDate date, Transaction t) {
		StringBuilder sb = new StringBuilder();
		sb.append("\t" + t.getTransactionType().getStringValue() + "\t");
		sb.append(toDollars(t.getAmount())+"\n");
		return sb.toString();
	}
	
	 public static String toDollars(double d){
	        return String.format("$%,.2f", abs(d));
	 }
	
	
}
