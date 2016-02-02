package com.abc;

public enum TransactionType {
	
	WITHDRAW("withdrawal"),
	DEPOSIT("deposit");
	
	private String stringValue;

	private TransactionType(String stringValue){
		this.stringValue = stringValue;
	}
	
	public String getStringValue(){
		return stringValue;
	}
}
