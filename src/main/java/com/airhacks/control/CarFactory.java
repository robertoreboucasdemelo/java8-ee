package com.airhacks.control;

import java.util.UUID;

import javax.inject.Inject;

import com.airhacks.entity.Car;
import com.airhacks.entity.Specification;
import com.airhacks.enums.Color;

public class CarFactory {
	
	@Inject
	Color defaultCarColor;
	
	public Car createCar(Specification specification) {
		Car car = new Car();
		car.setIdentifier(UUID.randomUUID().toString());
		car.setColor(specification.getColor() == null ? defaultCarColor : specification.getColor());
		car.setEngineType(specification.getEngineType());
		return car;
	}

}
