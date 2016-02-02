package com.abc;

import java.util.List;

public class CustomerSummaryBuilder {

	public static final String ACCOUNT = "account";
	public static final String ACCOUNTS = "accounts";
	public static final String CUSTOMER_SUMMARY_HEADING = "Customer Summary";

	public static String buildCustomerSummary(List<Customer> customers) {
		String summary = CUSTOMER_SUMMARY_HEADING;
        for (Customer c : customers)
            summary += createCustomerEntry(c);
        return summary;
    }

	public static String createCustomerEntry(Customer c) {
		return "\n - " + c.getName() + " (" + getSingularorPluralAccount(c.getNumberOfAccounts()) + ")";
	}

    //Make sure correct plural of word is created based on the number passed in:
    //If number passed in is 1 just return the word otherwise add an 's' at the end
    public static String getSingularorPluralAccount(int number) {
        return number + " " + (number == 1 ? ACCOUNT : ACCOUNTS);
    }

}
