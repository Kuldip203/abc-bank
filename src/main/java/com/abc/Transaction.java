package com.abc;


public class Transaction {
    private final double amount;
    private final ImmutableDate transactionDate;
    private final TransactionType type;

    public Transaction(double amount) {
        this.amount = amount;
        this.transactionDate = DateProvider.getInstance().now();
        if(amount > 0){
        	this.type = TransactionType.DEPOSIT;
        }
        else {
        	this.type = TransactionType.WITHDRAW;
        }
    }

	public double getAmount() {
		return amount;
	}

	public ImmutableDate getTransactionDate() {
		return transactionDate;
	}
	
	public TransactionType getTransactionType(){
		return type;
	}

}
