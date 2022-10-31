package com.airhacks.entity;

public class ProcessTracker {

	public void track(Category value) {
		System.out.println("value = " + value);
	}
	
	public enum Category{
		MANUFACTURER, UNUSED;
	}


}
