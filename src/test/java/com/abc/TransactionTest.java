package com.abc;


import org.junit.Assert;
import org.junit.Test;

public class TransactionTest {
    @Test
    public void testTransactionType() {
        Transaction t = new Transaction(5);
        Assert.assertEquals(TransactionType.DEPOSIT, t.getTransactionType());
        
        t = new Transaction(-5);
        Assert.assertEquals(TransactionType.WITHDRAW, t.getTransactionType());
        
    }
}
