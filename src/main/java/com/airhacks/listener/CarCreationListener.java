package com.airhacks.listener;

import java.util.concurrent.locks.LockSupport;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.enterprise.event.ObservesAsync;

import com.airhacks.entity.CarCreated;

public class CarCreationListener {
	
	public void onCarCreation(@ObservesAsync CarCreated carCreated) {
		LockSupport.parkNanos(2_000_000_000L);
		System.out.println("new car created with id" + carCreated.getIdentifier());
	}

}
