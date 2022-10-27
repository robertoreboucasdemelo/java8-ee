package com.airhacks.control;

import java.util.Random;
import java.util.UUID;

import javax.inject.Inject;

import com.airhacks.entity.Car;
import com.airhacks.entity.Specification;
import com.airhacks.enums.Color;
import com.airhacks.exception.CarCreationException;

public class CarFactory {
	
	@Inject
	@Diesel
	Color defaultCarColor;
	
	public Car createCar(Specification specification) {
		
		// Force Exception
		
		if (new Random().nextBoolean())
			throw new CarCreationException("Could Not Create Car!");
		
		Car car = new Car();
		car.setIdentifier(UUID.randomUUID().toString());
		car.setColor(specification.getColor() == null ? defaultCarColor : specification.getColor());
		car.setEngineType(specification.getEngineType());
		return car;
	}

}
