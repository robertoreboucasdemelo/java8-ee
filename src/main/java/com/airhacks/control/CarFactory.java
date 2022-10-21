package com.airhacks.control;

import java.util.UUID;

import com.airhacks.entity.Car;
import com.airhacks.entity.Specification;

public class CarFactory {
	
	
	
	public Car createCar(Specification specification) {
		Car car = new Car();
		car.setIdentifier(UUID.randomUUID().toString());
		car.setColor(specification.getColor());
		car.setEngineType(specification.getEngineType());
		return car;
	}

}
