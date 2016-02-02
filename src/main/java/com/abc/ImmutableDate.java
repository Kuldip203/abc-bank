package com.abc;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class ImmutableDate implements Comparable{

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/DD/YYYY");
	
	private final Date date;
			
	public Date getDate(){
		return (Date) date.clone();
	}
	public ImmutableDate(long time) {
		this.date = new Date(time);
	}
	
	public String getStringDate(){
		return dateFormat.format(date);
	}

	@Override
	public int compareTo(Object o) {
		ImmutableDate input = (ImmutableDate)o;
		return this.date.compareTo(input.getDate());
	}
	
	@Override
	public int hashCode(){
		return this.date.hashCode();
	}
	
	@Override
	public boolean equals(Object o){
		return this.getDate().equals(o);
	}
	
	public boolean isRecentThanNdays(int n){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -n);
		
		Calendar thisDate = Calendar.getInstance();
		thisDate.setTime(date);
		
		if(cal.before(thisDate)){
			return true;
		}
		else {
			return false;
		}
	}
	

}
