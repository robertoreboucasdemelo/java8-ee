package com.airhacks.boundary;


import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;


import com.airhacks.control.CarFactory;
import com.airhacks.control.CarRepository;
import com.airhacks.entity.Car;
import com.airhacks.entity.CarCreated;
import com.airhacks.entity.Specification;
import com.airhacks.enums.EngineType;

@Stateless
public class CarManufacturer {
	
	@Inject
	CarFactory carFactory;
	
	@Inject
	CarRepository carRepository;
	
	@Inject
	Event<CarCreated> carCreated;
	
	public Car manufactureCar(Specification specification) {
		Car car = carFactory.createCar(specification);
		carRepository.store(car);
		carCreated.fire(new CarCreated(car.getIdentifier()));
		return car;
	}

	public List<Car> retrieveCars(EngineType engineType) {
		return carRepository.loadCars();
	}

	public Car retrieveCar(String identifier) {
		// TODO Auto-generated method stub
		return null;
	}

}
