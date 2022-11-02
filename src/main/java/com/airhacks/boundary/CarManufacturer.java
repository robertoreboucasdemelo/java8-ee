package com.airhacks.boundary;


import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.airhacks.cache.CarCache;
import com.airhacks.control.CarFactory;
import com.airhacks.control.CarRepository;
import com.airhacks.entity.ProcessTracker;
import com.airhacks.entity.Car;
import com.airhacks.entity.CarCreated;
import com.airhacks.entity.Specification;
import com.airhacks.enums.EngineType;
import com.airhacks.interceptor.ProcessTrackingInterceptor;
import com.airhacks.interceptor.Tracked;
import com.airhacks.logger.FataLogger;
import com.airhacks.processor.CarProcessor;

@Stateless
public class CarManufacturer {
	
	@Inject
	CarFactory carFactory;
	
	@Inject
	CarRepository carRepository;
	
	// Download By maven JCache
	@Inject
	CarCache carCache;
	
	@Inject
	FataLogger fatalLogger;
	
	@Inject
	CarProcessor carProcessor;
	
	@PersistenceContext(unitName="persistenceUnitName")
	EntityManager entityManager;
	
	@Inject
	Event<CarCreated> carCreated;
	
//	@TransactionAttribute()
//	@Interceptors(ProcessTrackingInterceptor.class)
	@Tracked(ProcessTracker.Category.MANUFACTURER)
	public Car manufactureCar(Specification specification) {
		Car car = carFactory.createCar(specification);
		entityManager.persist(car);
//		carRepository.store(car);
//		carCreated.fire(new CarCreated(car.getIdentifier()));
//		carCache.cache(car);
		
//		try {
//			 
//		 } catch(Exception e) {
//			 fatalLogger.fatal(e);
//		 }
		
//		Future<String> resultFuture = carProcessor.processNewCar(car);
//		try {
//			resultFuture.get();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		carCreated.fireAsync(new CarCreated(car.getIdentifier()));
		
		
		return car;
	}

	public List<Car> retrieveCars(EngineType engineType) {
		//return carRepository.loadCars();
		//return carCache.retrieveCars();
		return entityManager.createNamedQuery(Car.FIND_ALL, Car.class).getResultList();
	}

	public Car retrieveCar(String identifier) {
		// TODO Auto-generated method stub
		return null;
	}

}
