package com.airhacks.control;

import java.util.Random;
import java.util.UUID;

import javax.inject.Inject;
import javax.transaction.Transactional;

import com.airhacks.config.Config;
import com.airhacks.entity.Car;
import com.airhacks.entity.Specification;
import com.airhacks.enums.Color;
import com.airhacks.exception.CarCreationException;
import com.airhacks.exception.CarStorageException;

public class CarFactory {
	
	@Inject
	@Diesel
	Color defaultCarColor;
	
	@Config("identifier.prefix")
	String identifierPrefix;
	
//	@Transactional(rollbackOn = CarStorageException.class)
	public Car createCar(Specification specification) {
		
		// Force Exception
		
//		if (new Random().nextBoolean())
//			throw new CarCreationException("Could Not Create Car!");
		
		Car car = new Car();
		car.setIdentifier(identifierPrefix + UUID.randomUUID().toString());
		car.setColor(specification.getColor() == null ? defaultCarColor : specification.getColor());
		car.setEngineType(specification.getEngineType());
		return car;
	}

}
