package com.airhacks.listener;

import javax.enterprise.event.Observes;

import com.airhacks.entity.CarCreated;

public class CarCreationListener {
	
	public void onCarCreation(@Observes CarCreated carCreated) {
		// .. some logic
		System.out.printl("new car created with id" + carCreated.getIdentifier());
	}

}
