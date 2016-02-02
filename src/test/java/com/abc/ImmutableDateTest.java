package com.abc;

import java.util.Calendar;

import org.junit.Assert;

import org.junit.Test;

public class ImmutableDateTest {

	@Test
	public void testIsBeforeNdays(){
		
		Calendar testDate = Calendar.getInstance();
		testDate.add(Calendar.DAY_OF_MONTH, -5);
		
		ImmutableDate date = new ImmutableDate(testDate.getTimeInMillis());
		
		Assert.assertTrue(date.isRecentThanNdays(10));
		
		Assert.assertFalse(date.isRecentThanNdays(5));
		
	}
}
