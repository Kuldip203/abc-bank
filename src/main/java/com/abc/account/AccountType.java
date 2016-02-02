package com.abc.account;

public enum AccountType {
	
	CHECKING("Checking Account") {
		@Override
		public Account createAccount() {
			return new CheckingAccount(this);
		}
	},
	SAVINGS("Savings Account") {
		@Override
		public Account createAccount() {
			return new SavingsAccount(this);
		}
	},
	MAXI_SAVINGS("Maxi Savings Account") {
		@Override
		public Account createAccount() {
			return new MaxiSavingsAccount(this);
		}
	};
	
	private String stringValue;

	AccountType(String stringValue){
		this.stringValue = stringValue;
	}
	
	public String getStringValue(){
		return this.stringValue;
	}
	
	public abstract Account createAccount();
}
