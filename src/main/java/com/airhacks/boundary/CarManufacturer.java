package com.airhacks.boundary;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.airhacks.control.CarFactory;
import com.airhacks.control.CarRepository;
import com.airhacks.entity.Car;
import com.airhacks.entity.Specification;

@Stateless
public class CarManufacturer {
	
	@Inject
	CarFactory carFactory;
	
	@Inject
	CarRepository carRepository;
	
	public Car manufactureCar(Specification specification) {
		Car car = carFactory.createCar(specification);
		carRepository.store(car);
		return car;
	}

}
