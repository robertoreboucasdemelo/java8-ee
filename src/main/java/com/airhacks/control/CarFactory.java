package com.airhacks.control;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.deploy.spi.Target;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.transaction.Transactional;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.airhacks.config.Config;
import com.airhacks.entity.Car;
import com.airhacks.entity.Specification;
import com.airhacks.enums.Color;
import com.airhacks.exception.CarCreationException;
import com.airhacks.exception.CarStorageException;
import com.airhacks.client.IndetifierAccessor;

public class CarFactory {
	
	@Inject
	@Diesel
	Color defaultCarColor;
	
	@Config("identifier.prefix")
	String identifierPrefix;
	
	@Resource
	ManagedScheduledExecutorService managed;
	
	@Inject 
	IndetifierAccessor indetifierAccessor;
	
//	@Transactional(rollbackOn = CarStorageException.class)
	public Car createCar(Specification specification) {
		
		// Force Exception
		
//		if (new Random().nextBoolean())
//			throw new CarCreationException("Could Not Create Car!");
		
		Car car = new Car();
		car.setIdentifier(identifierPrefix + UUID.randomUUID().toString());
		car.setIdentifier(indetifierAccessor.retrieveCarIdentification(specification));
		car.setColor(specification.getColor() == null ? defaultCarColor : specification.getColor());
		car.setEngineType(specification.getEngineType());
		return car;
	}
	
	@PostConstruct
	public void activateTimer() {
		managed.scheduleAtFixedRate(this::doSomething, 60, 10, TimeUnit.SECONDS);
	}
	
	public void doSomething() {
		System.out.println("print something");
	}
	
	
	
	

}
