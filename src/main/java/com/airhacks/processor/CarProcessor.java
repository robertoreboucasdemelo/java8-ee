package com.airhacks.processor;

import java.util.concurrent.Future;
import java.util.concurrent.locks.LockSupport;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

import com.airhacks.entity.Car;

@Stateless
public class CarProcessor {
	
	@Asynchronous
	public void processNewCarAsync(Car car) {
		LockSupport.parkNanos(2_000_000_000L);
		String result = "processed" + car;
		System.out.println(result);
	}
	
	public void processNewCar(Car car) {
		LockSupport.parkNanos(2_000_000_000L);
		String result = "processed" + car;
		System.out.println(result);
	}

}
