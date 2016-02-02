package com.abc;


public class DateProvider{
	
    private static DateProvider instance = null;
    
    private DateProvider(){
    	
    }

    public static DateProvider getInstance() {
        if (instance == null) {
        	synchronized(DateProvider.class){
        		if(instance == null) {
        			instance = new DateProvider();
        		}
        	}
        }
        return instance;
    }

	public ImmutableDate now() {
		return new ImmutableDate(System.currentTimeMillis());
    }
	
}
