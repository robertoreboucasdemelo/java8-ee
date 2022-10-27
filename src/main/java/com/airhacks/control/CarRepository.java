package com.airhacks.control;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.airhacks.entity.Car;
import com.airhacks.enums.Color;
import com.airhacks.enums.EngineType;

public class CarRepository {
	
	public void store(Car car) {
		//dummy error on creation
		if (new Random().nextBoolean())
			throw new IllegalStateException("Could Not Persist Car");
	}

	public List<Car> loadCars() {
		// dummy creation
		return Arrays.asList(
				createCar("X213345", Color.RED, EngineType.DIESEL),
				createCar("X973457", Color.BLACK, EngineType.ELETRIC),
				createCar("X458532", Color.GREY, EngineType.PETROL));
				
	}
	
	private static Car createCar(String identifier, Color color, EngineType engineType) {
		Car car = new Car();
		car.setIdentifier(identifier);
		car.setColor(color);
		car.setEngineType(engineType);
		return car;
	}

}
